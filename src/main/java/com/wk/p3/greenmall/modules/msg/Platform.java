package com.wk.p3.greenmall.modules.msg;

/**
 * Created by cc on 15-12-24.
 */


public enum Platform {

    //在数据字典中配置相关记录
    PcWeb("pc网页",1),
    PhoneMSG("手机短信",2),
    Mp("微信公众号",3),
    Android("android客户端",4),
    Ios("ios平台",5);

    private String description;
    private int index;

    private Platform(String description,int index){
        this.description = description;
        this.index = index;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.index+"";
    }

    //enum的值
    public int toValue() {
        return this.index;
    }

    public static Platform parse(int i){
        switch(i){
            case 1: return Platform.PcWeb;
            case 2: return Platform.PhoneMSG;
            case 3: return Platform.Mp;
            case 4: return Platform.Android;
            case 5: return Platform.Ios;
            default:return null;
        }
    }

}

