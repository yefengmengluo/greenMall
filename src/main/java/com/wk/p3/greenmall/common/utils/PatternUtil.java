package com.wk.p3.greenmall.common.utils;

import java.util.regex.Pattern;

/**
 * Created by cc on 16-1-15.
 */
public class PatternUtil {

    private static final Pattern qqPattern = Pattern.compile("^[1-9][0-9]{4,}$");

    private static final Pattern namePattern = Pattern.compile("^([\\u4e00-\\u9fa5]{1,20}|[a-zA-Z\\.\\s]{3,20})$");

    private static final Pattern mobilePattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$");

    private static final Pattern faxNumberPattern = Pattern.compile("^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$");

    private static final Pattern phonePattern = Pattern.compile("^(\\d{3,4}-?)?\\d{7,9}$");

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9_]{6,18}$");
    
    private static final Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");


    public static boolean isQQ(String qq){
        return qqPattern.matcher(qq).find();
    }

    public static boolean isPhone(String phone){
        return phonePattern.matcher(phone).find();
    }

    public static boolean isFaxNumber(String number){
        return faxNumberPattern.matcher(number).find();
    }

    public static boolean isPassword(String name){
        return passwordPattern.matcher(name).find();
    }

    public static boolean isUserName(String name){
        return namePattern.matcher(name).find();
    }
    
    public static boolean isEmail(String name){
    	return emailPattern.matcher(name).find();
    }



}
