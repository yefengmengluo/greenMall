package com.wk.p3.greenmall.modules.msg.impl.sms;

import com.wk.p3.greenmall.modules.msg.exception.ErrorException;
import com.wk.p3.greenmall.modules.msg.exception.FailException;
import com.wk.p3.greenmall.modules.msg.exception.HttpSendException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 15-12-24.
 */
public class Sms{

    //登录系统用户名，必填(任意字符)
    public String user="zs_wangku03";
    //系统分配，传输时要md5加密，必填(任意字符)
    //md5(wangku,32) = 32e4434a13f80e18f47491cd1019e6b6
    public String password="32e4434a13f80e18f47491cd1019e6b6";
    //发送手机号码，可以是多个（最大支持1000个号码），手机号之间用英文","分割 必填
    public List<String> tele;
    //Get方式提交内容要URLEncode一下，用GBK编码,Post方式提交内容不需要编码。必填。
    public String msg;
    //扩展号码，可选 加上通道自带的扩展号，最长不能超过20位。(数字)
    public String extno;
    //此参数可选，参数等于1时，如果短信内容超过限制的字数，同时该通道不支持长短信时，系统会自动将短信分成多条，并在每条的前面加上序号。例如：1/3)... 2/3)... 3/3)...
    public String addseqno="1";
    //发送一次返回的信息结果id，用于后续查询
    public String mid;
    //存放发送结果原始数据
    public String result;
    //存放发送成功短信
    public List<String> successTels = new ArrayList<String>();
    public String error;
    public String fail;


    public Sms(List<String> tels, String msg) {
        this.tele = tels;
        this.msg = msg;
    }

    /**
     * 成功
     成功时返回的数据
     ok:mid:tele
     ok:mid:tele,tele（多个号码返回格式）

     失败
     失败时返回的数据
     error:错误描述
     错误描述说明：
     1.用户名或密码错误--用户名为空
     2.扩展号码错误--扩展号不符合规则
     3.mobile--手机号码为空
     4.smg--信息内容为空
     6.无此用户或者密码不对1--无此用户
     7.无此用户或者密码不对--密码不匹配
     8.moblie 提交数小于10，请重新提交!--限制提交为10条
     9.moblie is null or mobile number too many!--提交号码数量过多，或者为空，限制上限1000

     错误
     出错时返回的数据
     fail:错误代码码:tele
     fail:错误代码码:tele1,tele2
     错误代码说明：
     999 --系统错误

     * @param result
     * @throws ErrorException
     * @throws FailException
     * @throws HttpSendException
     */
    public void parasResult(String result) throws ErrorException, FailException, HttpSendException {
       this.result = result;
       if(result.startsWith("ok:")){
           //一个成功的短信回复为：ok:mid:tele,tele
           String[] strs = result.split(":");
           this.mid = strs[1];
           String telstrings = strs[2];
           for(String s:telstrings.split(",")){
               successTels.add(s);
           }
       }else if(result.startsWith("error:")){
           this.error = result;
           //throw new FailException("失败时返回的数据:"+result.toString());
       }else if(result.startsWith("fail:")){
           this.error = result;
           //throw new ErrorException("出错时返回的数据:"+result.toString());
       }else{
           throw new HttpSendException("返回数据异常，数据不在文档范围，可能是接口地址错误"+result.toString());
       }

    }

    /**
     * list String 拼接成所有元素用逗号连接的字符串
     * @param tele
     * @return
     */
    private String telToString(List<String> tele){
        StringBuffer sb =  new StringBuffer();
        for(int i=0;i<tele.size();i++){
            sb.append(tele.get(i));
            if(i!=tele.size()-1){
                sb.append(',');
            }
        }
        return sb.toString();
    }

    public String getTelString(){
        return telToString(this.tele);
    }

    /**
     * 发送电话和成功发送电话用:分割
     * eg.  电话1，电话2，电话3：电话1，电话3
     * 其中电话2没有发送成功
     * @return
     */
    public String getResultTel(){
        return telToString(this.tele)+":"+telToString(this.successTels);
    }


    //转换为map
    public Map<String,String> toMap(){
        Map map = new HashMap<String,String>();
        map.put("user",user);
        map.put("password",password);
        map.put("tele",getTelString());
        map.put("msg", msg);
        map.put("extno",extno);
        map.put("addseqno",addseqno);
        return map;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTels() {
        return tele;
    }

    public void setTels(List<String> tels) {
        this.tele = tels;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    public String getAddseqno() {
        return addseqno;
    }

    public void setAddseqno(String addseqno) {
        this.addseqno = addseqno;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", tele=" + tele +
                ", msg='" + msg + '\'' +
                ", extno='" + extno + '\'' +
                ", addseqno='" + addseqno + '\'' +
                ", mid='" + mid + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
