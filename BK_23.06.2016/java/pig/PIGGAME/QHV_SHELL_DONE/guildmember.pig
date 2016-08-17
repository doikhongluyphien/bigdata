register '/home/java/filejar/protobuf-java-2.6.1.jar';
register '/home/java/filejar/pigjavamainv2.jar';

%declare ALIAS 'qhv';

STOCK_A = LOAD '$ALIAS.player$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() ;
STOCK_B = LOAD '$ALIAS.gang$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() ;
STOCK_G = LOAD 'graph_mobo_vn.account_service_$gameid' using org.apache.hive.hcatalog.pig.HCatLoader(); 


STOCK_A1 = FILTER STOCK_A BY $2 IS NOT NULL AND $5>=30;
STOCK_A2 = FOREACH STOCK_A1 GENERATE $0 as id,$1 as userid,FLATTEN(STRSPLIT($2, '_')) as (a1:chararray, msi:chararray),
$4 as name,$5 as lv,$6 as createdate,$7 as logindate,
ToString(CurrentTime(),'yyyy-MM-dd HH:mm:ss') as date:chararray ;

STOCK_B1 = FOREACH STOCK_B GENERATE id,FLATTEN(myudfs.ParseprotoQHVGuildMember(protodata)#'value') as proto;

STOCK_A_B = JOIN STOCK_A2 BY $0, STOCK_B1 BY $1#'roleid';

STOCK_G0 = GROUP STOCK_G BY (mobo_id,mobo_service_id,fullname);
STOCK_G1_1 = FILTER STOCK_G0 BY group.$0 IS NOT NULL And group.$1 IS NOT NULL;
STOCK_G2 = FOREACH STOCK_G1_1 GENERATE group.$0 as mobo_id,(chararray)group.$1 AS mobo_service_id,group.$2 as fullname;

STOCK_A_B_G = JOIN STOCK_A_B BY $3, STOCK_G2 BY $1;
--INFOMATION NEED
STOCK_FINAL = FOREACH STOCK_A_B_G GENERATE CONCAT('$ALIAS',CONCAT('_',CONCAT((chararray)$serverid,CONCAT('_',(chararray)$9)))) as game_guild_id,
$8 as date,myudfs.UnixtimetoDate($10#'joindate') as join_date,$12 as msi_mem,$12 as accid_mem,$11 as mobo_id,(chararray)$13 as fullname,
(chararray)$0 as role_id_mem,0 as statussend;
--STORE STOCK_FINAL INTO 'bigdata.guild_member' USING org.apache.hive.hcatalog.pig.HCatStorer();


dump STOCK_FINAL;
/*
DUMP STOCK_B1;
--STORE STOCK_A2 INTO 'output/QHV/testgang';
*/
