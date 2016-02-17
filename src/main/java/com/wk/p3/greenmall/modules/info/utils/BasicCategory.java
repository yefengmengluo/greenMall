package com.wk.p3.greenmall.modules.info.utils;

/**
 * Created by zhuyanqing on 2015/12/22.
 * 基础类别标识（code）
 */
public enum BasicCategory {

    agricultural("agricultural", "农产品"),
    fruit("fruit", "果品"),
    vegetable("vegetable", "果果"),
    chineseHerbalMedicine("chineseHerbalMedicine", "中草药"),
    moslemFood("moslemFood", "清真食品"),
    gaoyuanxiacai("gaoyuanxiacai", "高原夏菜");


    private final String code;
    private final String desc;

    private BasicCategory(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static BasicCategory valueOfCode(String code) {
        for (BasicCategory df : BasicCategory.values()) {
            if (df.getCode().equals(code)) {
                return df;
            }
        }
        return null;
    }

}
