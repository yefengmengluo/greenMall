package com.wk.p3.greenmall.modules.msg.impl.sms;

import com.wk.p3.greenmall.modules.msg.entity.MsgSms;
import com.wk.p3.greenmall.modules.msg.exception.HttpSendException;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.service.MsgSmsService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 15-12-24.
 */

@Service
public class SmsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //timeout for waiting for data
    private int socketTimeout = 2000;
    //timeout of connection is established
    private int connectTimeout = 2000;
    private String sendUrl = "http://58.83.147.92:8080/qxt/smssenderv2";


    @Autowired
    MsgSmsService msgSmsService;

    /**
     * httpclient发送方法(统一使用post方法)
     *
     * @param map    参数名值对
     * @param apiUrl api对应url地址
     * @return
     * @throws IOException
     */
    private String send(Map<String, String> map, String apiUrl) throws IOException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, Charset.forName("GBK"));
        HttpPost httppost = new HttpPost(apiUrl);
        httppost.setEntity(formEntity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .build();
        httppost.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httppost);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return result;
            } else {
                return "";
            }
        } finally {
            response.close();
        }
    }

    /**
     * 短信下发接口
     * 接口类型：HTTP GET/POST
     * 接口名称：短信下发HTTP接口
     * 接口通信地址：http://58.83.147.92:8080/qxt/smssenderv2
     */
    public void sendSMS(Sms sms) throws SendException {
        logger.debug("发送短信消息:[" + sms.toString() + "][" + sendUrl + "]");
        String result = null;
        try {
            result = send(sms.toMap(), sendUrl);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpSendException("发送消息短信接口失败:"+e.getMessage());
        }
        sms.parasResult(result);
        MsgSms msgSms = new MsgSms();
        msgSms.setMsg(sms.getMsg());
        msgSms.setTele(sms.getTelString());
        msgSms.setResult(result);
        msgSms.setMid(sms.getMid());
        msgSmsService.save(msgSms);
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }
}
