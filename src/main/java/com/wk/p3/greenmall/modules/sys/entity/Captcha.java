package com.wk.p3.greenmall.modules.sys.entity;

import java.io.Serializable;

/**
 * Created by cc on 15-12-8.
 */
public class Captcha implements Serializable{

    /**
     * 每个验证码对象对应的唯一Id
     */
    public String Id;
    /**
     * 该验证码对应的字符串
     */
    public String code;
    /**
     * 验证码图片字节流
     */
    public byte[] fileContent;



    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public byte[] getFileContent() {
        return fileContent;
    }
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
