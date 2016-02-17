package com.wk.p3.greenmall.modules.user;

/**
 * Created by cc on 15-12-10.
 */
public enum  LoginType {
    mp,             //微信公众号登陆用户
    admin,          //后台管理登录用户
    pcWeb,          //pc端浏览器前台登录用户
    wap,            //手机浏览器登录用户
    android,        //android用户
    ios             //ios用户
    ;

    public static LoginType parse(String loginType) {
        if("mp".equals(loginType)){
            return LoginType.mp;
        }else if("admin".equals(loginType)){
            return LoginType.admin;
        }else if("pcWeb".equals(loginType)){
            return LoginType.pcWeb;
        }else if("wap".equals(loginType)){
            return LoginType.wap;
        }else if("android".equals(loginType)){
            return LoginType.android;
        }else if("ios".equals(loginType)){
            return LoginType.ios;
        }else{
            return null;
        }
    }

//    @Override
//    public String toString() {
//        return this.getClass().getSimpleName();
//    }
}
