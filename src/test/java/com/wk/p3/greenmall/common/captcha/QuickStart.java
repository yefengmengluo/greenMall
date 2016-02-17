package com.wk.p3.greenmall.common.captcha;

import com.github.cage.Cage;
import com.github.cage.GCage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cc on 15-12-8.
 */
public class QuickStart {

    public static void main(String[] args) throws IOException {
        final Cage cage = new GCage();

        final OutputStream os = new FileOutputStream("captcha.jpg", false);
        try {
            String code = cage.getTokenGenerator().next();
            System.out.println("code:"+code);
            cage.draw(code, os);
        } finally {
            os.close();
        }
    }

}
