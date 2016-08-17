package myudfs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Hex;

public class UTF8 {
	
	public static void main(String[] args) throws Exception {
		String str = "7b 22 61 72 6d 79 5f 6e 61 6d 65 22 3a 22 56 c5 a9 20 54 c6 b0 e1 bb 9b 63 22 2c 22 61 72 6d 79 5f 6c 65 76 65 6c 22 3a 32 2c 22 61 72 6d 79 5f 62 61 64 67 65 5f 74 79 70 65 22 3a 31 2c 22 61 72 6d 79 5f 74 65 72 72 61 69 6e 5f 74 79 70 65 22 3a 30 2c 22 61 72 6d 79 5f 66 75 6e 64 22 3a 30 2c 22 66 75 6e 64 5f 72 65 63 6f 72 64 22 3a 30 2c 22 61 72 6d 79 5f 63 6f 6e 73 74 72 75 63 74 69 6f 6e 22 3a 32 34 35 33 38 2c 22 63 72 65 61 74 65 5f 74 69 6d 65 22 3a 31 34 35 35 30 33 33 36 30 32 2c 22 77 69 6e 5f 72 65 63 6f 72 64 22 3a 30 2c 22 70 65 74 5f 68 6f 75 73 65 5f 6c 65 76 65 6c 22 3a 30 2c 22 70 65 74 5f 68 6f 75 73 65 5f 63 6f 6e 73 74 72 75 63 74 69 6f 6e 22 3a 30 2c 22 61 72 6d 79 5f 6e 61 74 69 6f 6e 22 3a 32 2c 22 61 72 6d 79 5f 61 70 70 6c 79 5f 74 79 70 65 22 3a 32 2c 22 61 72 6d 79 5f 61 70 70 6c 79 5f 6c 65 76 65 6c 22 3a 33 30 2c 22 61 72 6d 79 5f 61 70 70 6c 79 5f 63 61 70 61 63 69 74 79 22 3a 31 30 30 30 2c 22 61 72 6d 79 5f 64 61 79 5f 69 64 22 3a 32 35 31 37 38 39 34 32 38 2c 22 61 72 6d 79 5f 72 65 63 72 75 69 74 5f 64 61 79 5f 63 6f 75 6e 74 22 3a 30 2c 22 61 72 6d 79 5f 73 70 72 69 74 65 5f 76 61 6c 75 65 22 3a 32 33 33 35 2c 22 61 72 6d 79 5f 73 70 72 69 74 65 5f 74 72 65 65 5f 72 69 70 65 5f 74 69 6d 65 73 22 3a 30 2c 22 6e 6f 74 69 63 65 22 3a 22 4d 2e 4e 20 41 49 20 56 41 cc 80 4f 20 54 48 45 cc 80 20 50 48 c6 af cc 89 49 20 47 4f cc 81 50 20 4e 48 45 cc 81 2e 20 4b 20 47 4f cc 81 50 20 53 45 cc 83 20 42 49 cc a3 20 c4 90 55 c3 94 cc 89 49 21 4d 2e 4e 20 54 48 c3 94 4e 47 20 43 41 cc 89 4d 2e 22 2c 22 79 79 5f 67 72 6f 75 70 22 3a 22 22 2c 22 71 71 5f 67 72 6f 75 70 22 3a 22 22 2c 22 74 65 72 72 61 69 6e 5f 70 61 72 61 6d 22 3a 7b 22 6b 65 79 22 3a 7b 22 74 65 72 72 61 69 6e 5f 61 63 74 69 76 65 22 3a 66 61 6c 73 65 7d 2c 22 76 61 6c 75 65 22 3a 5b 5b 74 72 75 65 5d 2c 5b 66 61 6c 73 65 5d 2c 5b 66 61 6c 73 65 5d 2c 5b 66 61 6c 73 65 5d 5d 7d 2c 22 61 72 6d 79 5f 73 6b 69 6c 6c 5f 70 61 72 61 6d 22 3a 5b 34 2c 34 2c 34 2c 34 2c 34 2c 34 2c 34 2c 34 2c 34 5d 2c 22 61 72 6d 79 5f 6f 77 6e 5f 62 61 64 67 65 22 3a 5b 74 72 75 65 2c 74 72 75 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 2c 66 61 6c 73 65 5d 2c 22 67 6f 61 6c 5f 70 61 72 61 6d 22 3a 7b 22 6b 65 79 22 3a 7b 22 67 6f 61 6c 5f 69 6e 64 65 78 22 3a 30 2c 22 67 6f 61 6c 5f 66 69 6e 69 73 68 65 64 22 3a 31 2c 22 67 6f 61 6c 5f 72 65 77 61 72 64 22 3a 32 2c 22 67 6f 61 6c 5f 72 65 77 61 72 64 5f 74 69 6d 65 22 3a 33 7d 2c 22 76 61 6c 75 65 22 3a 5b 5b 30 2c 74 72 75 65 2c 66 61 6c 73 65 2c 30 5d 2c 5b 31 2c 74 72 75 65 2c 66 61 6c 73 65 2c 30 5d 5d 7d 2c 22 74 61 72 67 65 74 5f 70 61 72 61 6d 22 3a 7b 22 74 61 72 67 65 74 5f 66 69 6e 69 73 68 5f 76 61 6c 75 65 22 3a 30 2c 22 74 61 72 67 65 74 5f 70 72 65 5f 66 69 6e 69 73 68 5f 76 61 6c 75 65 22 3a 30 2c 22 74 61 72 67 65 74 5f 74 68 69 73 5f 77 65 65 6b 5f 74 69 6d 65 22 3a 31 34 35 37 38 38 38 34 30 33 2c 22 74 61 72 67 65 74 5f 76 61 6c 75 65 5f 6c 69 73 74 22 3a 5b 30 2c 30 2c 30 2c 30 2c 30 5d 2c 22 74 61 72 67 65 74 5f 72 65 77 61 72 64 5f 63 6f 75 6e 74 22 3a 30 7d 2c 22 61 63 74 69 76 69 74 79 5f 70 61 72 61 6d 22 3a 7b 22 6b 65 79 22 3a 7b 22 61 63 74 69 76 69 74 79 5f 74 79 70 65 22 3a 30 2c 22 61 63 74 69 76 69 74 79 5f 68 6f 75 72 22 3a 31 2c 22 61 63 74 69 76 69 69 74 79 5f 6d 69 6e 22 3a 32 7d 2c 22 76 61 6c 75 65 22 3a 5b 5d 7d 2c 22 61 72 6d 79 5f 62 6f 73 73 5f 70 61 72 61 6d 22 3a 7b 22 61 72 6d 79 5f 62 6f 73 73 5f 74 79 70 65 22 3a 30 2c 22 61 72 6d 79 5f 62 6f 73 73 5f 63 61 6c 6c 22 3a 66 61 6c 73 65 2c 22 61 72 6d 79 5f 62 6f 73 73 5f 63 61 6c 6c 5f 64 61 79 22 3a 32 35 31 37 38 39 34 32 38 2c 22 61 72 6d 79 5f 62 6f 73 73 5f 77 65 65 6b 5f 63 61 6c 6c 5f 74 69 6d 65 22 3a 30 2c 22 61 72 6d 79 5f 62 6f 73 73 5f 74 68 69 73 5f 77 65 65 6b 5f 74 69 6d 65 22 3a 31 34 35 37 38 38 38 34 30 33 7d 2c 22 61 72 6d 79 5f 63 61 72 72 69 61 67 65 5f 70 61 72 61 6d 22 3a 7b 22 61 72 6d 79 5f 63 61 72 72 69 61 67 65 5f 64 61 79 5f 69 64 22 3a 32 35 31 37 38 39 34 32 38 2c 22 61 72 6d 79 5f 63 61 72 72 69 61 67 65 5f 63 6f 75 6e 74 22 3a 30 7d 2c 22 61 72 6d 79 5f 73 65 63 72 65 74 5f 70 61 72 61 6d 22 3a 7b 22 61 72 6d 79 5f 73 65 63 72 65 74 5f 63 61 6c 6c 22 3a 66 61 6c 73 65 2c 22 61 72 6d 79 5f 73 65 63 72 65 74 5f 63 61 6c 6c 5f 64 61 79 22 3a 32 35 31 37 38 39 34 32 38 2c 22 61 72 6d 79 5f 73 65 63 72 65 74 5f 77 65 65 6b 5f 63 61 6c 6c 5f 74 69 6d 65 22 3a 30 2c 22 61 72 6d 79 5f 73 65 63 72 65 74 5f 74 68 69 73 5f 77 65 65 6b 5f 74 69 6d 65 22 3a 31 34 35 37 38 38 38 34 30 30 7d 2c 22 61 72 6d 79 5f 65 6e 65 6d 79 5f 70 61 72 61 6d 22 3a 7b 22 61 72 6d 79 5f 65 6e 65 6d 79 5f 6c 69 73 74 5f 70 61 72 61 6d 22 3a 5b 5b 34 2c 32 2c 32 34 2c 33 2c 32 37 5d 2c 5b 35 2c 30 2c 34 2c 31 2c 35 5d 2c 5b 31 32 2c 30 2c 35 2c 31 2c 36 5d 2c 5b 34 36 2c 35 2c 37 32 2c 35 2c 37 37 5d 2c 5b 32 33 31 2c 30 2c 32 30 2c 30 2c 32 30 5d 2c 5b 33 37 33 2c 30 2c 35 2c 30 2c 35 5d 2c 5b 33 38 34 2c 30 2c 35 30 2c 33 2c 35 33 5d 5d 7d 7d";
		ByteBuffer buff = ByteBuffer.allocate(str.length()/2);
		for (int i = 0; i < str.length(); i+=3) {
		    buff.put((byte)Integer.parseInt(str.substring(i, i+2), 16));
		}
		buff.rewind();
		Charset cs = Charset.forName("UTF-8");
		CharBuffer cb = cs.decode(buff);
	
		System.out.println(cb.toString());
		
	}

	/*
	 * public String exec(Tuple input) throws IOException { //String str =
	 * (String) input.get(0).toString(); String str="Jok�?�r";
	 * 
	 * String utf8 = str.toString(); byte[] b = utf8.getBytes("UTF-8");
	 * System.out.println(b.toString()); //return b.toString();
	 * 
	 * }
	 */
	
	
	
}
