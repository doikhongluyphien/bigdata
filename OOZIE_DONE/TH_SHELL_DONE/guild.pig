register '/usr/hdp/2.3.4.0-3485/pig/lib/piggybank.jar';
register '/home/java/filejar/pigjavamain.jar';
%declare ALIAS 'tieuhiep';
%declare ALIASGAME 'th';
STOCK_A = LOAD '$ALIAS.army$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() AS (army_id,army_attr,army_member,army_building_attr:chararray);
STOCK_B = LOAD '$ALIAS.army_brief$serverid' using org.apache.hive.hcatalog.pig.HCatLoader(); 
STOCK_D = LOAD '$ALIAS.role_name_map$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() AS (idrole_name_map,role_id,role_name,plat_user_name,ditch_name); 

STOCK_G = LOAD 'graph_mobo_vn.account_service_$gameid' using org.apache.hive.hcatalog.pig.HCatLoader(); 

--START PROCCESS
--STOCK_A1 = FOREACH STOCK_A GENERATE $0,myudfs.JsonMap(myudfs.ToJson(myudfs.TestHex($1))) as army_attr,myudfs.TestHex($2) as army_member,myudfs.TestHex($3) as army_building_attr, ToString(CurrentTime(),'yyyy-MM-dd HH:mm:ss') as date:chararray ; 
STOCK_A1 = FOREACH STOCK_A GENERATE $0,myudfs.JsonMap(myudfs.ToJson(myudfs.TestHex($1))) as army_attr,ToString(CurrentTime(),'yyyy-MM-dd HH:mm:ss') as date:chararray ; 
STOCK_A2 = FOREACH STOCK_A1 GENERATE $0,army_attr#'create_time' as create_time,date ; 
--PROCESS army_brief
STOCK_B2 = FOREACH STOCK_B GENERATE $0 as idarmy_brief,$1 as army_id,myudfs.TestHex($2) as army_name,$3 as army_level,$8 as leader_id,myudfs.TestHex($9) as leader_name ; 
--PROCESS role_name_map
STOCK_D2 = FOREACH STOCK_D GENERATE $0 as idrole_name_map,$1 as role_id,myudfs.TestHex($2) as role_name,FLATTEN(STRSPLIT(myudfs.TestHex($3), '_')) as (a1:chararray, plat_user_name:chararray),myudfs.TestHex($4) as ditch_name; 

STOCK_G0 = GROUP STOCK_G BY (mobo_id,mobo_service_id,fullname);
STOCK_G1_1 = FILTER STOCK_G0 BY group.$0 IS NOT NULL And group.$1 IS NOT NULL;
STOCK_G2 = FOREACH STOCK_G1_1 GENERATE group.$0 as mobo_id,(chararray)group.$1 AS mobo_service_id,group.$2 as fullname;


--JOIN army_brief & role_name_map
STOCK_B_D = JOIN STOCK_B2 BY leader_id, STOCK_D2 BY role_id;

--JOIN STOCK_B_D & army
STOCK_B_D_A = JOIN STOCK_B_D BY army_id, STOCK_A2 BY army_id;

STOCK_B_D_A_G = JOIN STOCK_B_D_A BY $10, STOCK_G2 BY mobo_service_id;

STOCK_FINAL = FOREACH STOCK_B_D_A_G GENERATE $14 as date,$14 as date_modify,$gameid as game_id,$serverid as server_id,CONCAT((chararray)'$ALIASGAME',CONCAT('_',CONCAT((chararray)$serverid,CONCAT('_',(chararray)$0)))) as game_guild_id,CONCAT((chararray)'$ALIASGAME',CONCAT('_',CONCAT((chararray)$serverid,CONCAT('_',(chararray)$2)))) as game_guild_name,myudfs.TimestamptoDate($13) as game_guild_create_date,$5 as game_guild_leader_name,$10 as msi_leader,$15 as mobo_id,$17 as fullname,$10 as accid_leader,(chararray)$4 as role_id_leader;

--STORE STOCK_FINAL INTO 'output/TH/test1';
--STOCK_B_D_A_G = JOIN STOCK_FINAL BY $8 LEFT OUTER, STOCK_G1 BY mobo_service_id;
--STOCK_FINAL_LAST = FOREACH STOCK_B_D_A_G GENERATE $10 as role_id_leader;
STORE STOCK_FINAL INTO 'bigdata.guild' USING org.apache.hive.hcatalog.pig.HCatStorer();