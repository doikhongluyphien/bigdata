package myudfs;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class LibMember extends EvalFunc<Map<String, Object>>{
	public static enum ERRORS {
        JSONParseError, JSONMappingError, EOFError, GenericError
    };

    private static final BagFactory bagFactory = BagFactory.getInstance();
    protected static final TupleFactory tupleFactory = TupleFactory.getInstance();
    protected final ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * Converts List objects to DataBag to keep Pig happy
     * 
     * @param l
     * @return
     */
    @SuppressWarnings("unchecked")
    private DataBag convertListToBag(List<Object> l) {
        DataBag dbag = bagFactory.newDefaultBag();
        DataBag dbbagtest = bagFactory.newDefaultBag();
        Tuple t = tupleFactory.newTuple();
        for (Object o : l) {
        	Tuple ap = tupleFactory.newTuple();
            if (o instanceof List) {
                dbag.addAll(convertListToBag((List<Object>) o));
            } else {
            	ap.append(o);
            	if (ap.size() > 0) {
            		dbag.add(ap);
            	}
            }
        }

        if (t.size() > 0) {
        }

        return dbag;
    }

    /**
     * Convert map and its values to types that Pig can handle
     * 
     * @param m
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> makeSafe(Map<String, Object> m) {
        Map<String, Object> safeValues = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : m.entrySet()) {
            Object v = entry.getValue();
            if (v != null && v instanceof List) {
                DataBag db = convertListToBag((List<Object>) v);
                safeValues.put(entry.getKey(), db);
            } else if (v != null && v instanceof Map) {
                safeValues.put(entry.getKey(), makeSafe((Map<String, Object>) v));
            } else {
                safeValues.put(entry.getKey(), entry.getValue());
            }
        }

        return safeValues;
    }

    @Override
    public Map<String, Object> exec(Tuple input) throws IOException {
        if (input == null || input.size() == 0) {
            return null;
        }

        try {
            Map<String, Object> values = jsonMapper.readValue((String) input.get(0),
                                                              new TypeReference<Map<String, Object>>() {});
            return makeSafe(values);
        } catch (JsonParseException e) {
            warn("JSON Parse Error: " + e.getMessage(), ERRORS.JSONParseError);
        } catch (JsonMappingException e) {
            warn("JSON Mapping Error: " + e.getMessage(), ERRORS.JSONMappingError);
        } catch (EOFException e) {
            warn("Hit EOF unexpectedly", ERRORS.EOFError);
        } catch (Exception e) {
            warn("Generic error during JSON mapping", ERRORS.GenericError);
        }

        return null;
    }
}
