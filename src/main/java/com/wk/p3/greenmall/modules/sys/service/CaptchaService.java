package com.wk.p3.greenmall.modules.sys.service;

import com.wk.p3.greenmall.modules.sys.entity.Captcha;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cc on 15-12-8.
 */
public interface CaptchaService {

    /**
     * 生成一个验证码
     * 通过给定一个输出流,输出验证码图片文件流，同时返回验证码中对应的验证码
     * @param outputStream 验证码字节流
     *     比如：HttpServletResponse.getOutputStream()
     * @return 验证码图片中字符串
     */
    public String generateCaptcha(OutputStream outputStream) throws IOException;


    /**
     * 从验证码池中取出生成好的验证码
     * @return 验证码图片中字符串
     */
    public Captcha getCaptcha();

}


