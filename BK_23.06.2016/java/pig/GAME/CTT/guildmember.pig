register '/usr/hdp/2.3.4.0-3485/pig/lib/mysql-connector-java-5.1.34.jar';
register '/usr/hdp/2.3.4.0-3485/pig/lib/piggybank.jar';
register '/home/java/filejar/pigjavamain.jar';

STOCK_B = LOAD 'ctt.t_juntuan_chengyuan$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() ;
STOCK_C = LOAD 'ctt.t_juese$serverid' using org.apache.hive.hcatalog.pig.HCatLoader() ; 
STOCK_D = LOAD 'ctt.t_zhanghao' using org.apache.hive.hcatalog.pig.HCatLoader() ;


--START PROCCESS

--PROCESS t_juntuan_chengyuan
STOCK_B1 = FOREACH STOCK_B GENERATE str_name,juntuan_id,dt_rutuan_time,jue_se,ToString(CurrentTime(),'yyyy-MM-dd HH:mm:ss') as date:chararray  ; 
--PROCESS t_juese
STOCK_C1 = FOREACH STOCK_C GENERATE zhang_hao,str_name,dt_chuangjian_time,dt_shangci_denglv,deng_ji,guid; 
--PROCESS t_zhanghao
STOCK_D1 = FOREACH STOCK_D GENERATE third_uname,guid; 

--JOIN t_juntuan_chengyuan & t_juese
STOCK_B_C = JOIN STOCK_B1 BY jue_se LEFT OUTER, STOCK_C1 BY guid;

--JOIN STOCK_B_C_D & t_zhanghao
STOCK_B_C_D = JOIN STOCK_B_C BY zhang_hao LEFT OUTER, STOCK_D1 BY guid;

--INFOMATION NEED
STOCK_FINAL = FOREACH STOCK_B_C_D GENERATE CONCAT((chararray)$gameid,CONCAT('_',CONCAT((chararray)$serverid,CONCAT('_',(chararray)$1)))) as game_guild_id,$4 as date,$2 as join_time,$10 as msi,$10 as accid_mem,$3;
STORE STOCK_FINAL INTO 'output/test121';
--STORE STOCK_FINAL INTO 'guild' USING org.apache.pig.piggybank.storage.DBStorage('com.mysql.jdbc.Driver','jdbc:mysql://10.10.20.13/datawarehouse','mapreduce','0XfAWJwiwb','INSERT INTO guild(`date` ,`date_modify` ,`game_id`,`server_id`,`game_guild_id`,`game_guild_name`,`game_guild_create_date`,`game_guild_leader_name`,`msi_leader`,`accid_leader`,`role_id_leader`) VALUES(?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE `date_modify` = ?');