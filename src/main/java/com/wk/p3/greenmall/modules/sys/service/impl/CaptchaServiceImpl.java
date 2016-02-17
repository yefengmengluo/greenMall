package com.wk.p3.greenmall.modules.sys.service.impl;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.wk.p3.greenmall.modules.sys.entity.Captcha;
import com.wk.p3.greenmall.modules.sys.service.CaptchaService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cc on 15-12-8.
 */

@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final Cage cage = new GCage();

    @Override
    public String generateCaptcha(OutputStream outputStream) throws IOException {
        String code = cage.getTokenGenerator().next();
        cage.draw(code,outputStream);
        return code;
    }

    @Override
    public Captcha getCaptcha() {
        //todo 后面实现一个验证码池，提高取验证码的效率
        return null;
    }

}
