/* 添加短信发送平台

    PcWeb("pc网页",1),
    PhoneMSG("手机短信",2),
    Mp("微信公众号",3),
    Android("android客户端",4),
    Ios("ios平台",5);

 */
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e01","1","PcWeb","MsgPlatform","pc网页",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e02","2","PhoneMSG","MsgPlatform","手机短信",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e03","3","Mp","MsgPlatform","微信公众号",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e04","4","Android","MsgPlatform","android客户端",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e05","5","Ios","MsgPlatform","ios平台",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");


/* 添加短信业务类型

    供求类:

 */


INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e06","1","supply","MsgCode","供应信息",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917a17","2","demand","MsgCode","求购信息",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917a18","3","forgetpd","MsgCode","忘记密码",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e19","4","regist","MsgCode","注册",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("4cd72d657d13426aad2eb2f708917e00","5","reset","MsgCode","重置密码",20,"1","2015-12-26 11:06:41.32","1","2015-12-26 11:06:41.32","remarks","0");



/* 数据字典添加用户类型 */
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("5cd72d657d13426aad2eb2f708917e00","4","PcWeb","sys_user_type","用户类型",40,"1","2013-05-27 08:00:00.00","1","2013-05-27 08:00:00.00",NULL,"0");


/* 数据字典添加 固定短信验证码配置
                String fixCode = DictUtils.getDictValue("fixCode","smsCode","true");
                String smsCode = DictUtils.getDictValue("code","smsCode","1234");
*/

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("5ad72d657d13426aad2eb2f708917e00","true","fixCode","smsCode","短信验证码配置",40,"1","2013-05-27 08:00:00.00","1","2013-05-27 08:00:00.00",NULL,"0");
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("5ad72d657d13426aad2eb2f708917e01","1234","code","smsCode","短信验证码配置",40,"1","2013-05-27 08:00:00.00","1","2013-05-27 08:00:00.00",NULL,"0");


/**
    数据字典添加用户类型
 */

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("5ad72d657a13426aad2eb2f708917e00","middle","middle_user","user_relation_type","用户关系的中间人(委托人)",40,"1","2013-05-27 08:00:00.00","1","2013-05-27 08:00:00.00",NULL,"0");
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag )
VALUES ("5ad72d657a13426aad2eb2f708917e01","friend","friend","user_relation_type","用户关系的中间人(朋友)",40,"1","2013-05-27 08:00:00.00","1","2013-05-27 08:00:00.00",NULL,"0");










