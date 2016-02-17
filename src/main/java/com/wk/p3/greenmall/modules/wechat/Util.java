package com.wk.p3.greenmall.modules.wechat;

import com.wk.p3.greenmall.modules.wechat.entity.mp.MpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.io.UnsupportedEncodingException;

/**
 * Created by cc on 15-11-27.
 */
public class Util {

    public static MpUser convert(WxMpUser wxMpUser) {
        MpUser mpUser = new MpUser();
        mpUser.setSubscribe(wxMpUser.getSubscribe()==null?"":(wxMpUser.getSubscribe()?"true":"false"));
        mpUser.setOpenid(wxMpUser.getOpenId());
        //emoji表情问题
        mpUser.setNickname(new String(wxMpUser.getNickname().getBytes()));
        mpUser.setSex(wxMpUser.getSex()==null?"":wxMpUser.getSex());
        mpUser.setLanguage(wxMpUser.getLanguage()==null?"":wxMpUser.getLanguage());
        mpUser.setCity(wxMpUser.getCity()==null?"":wxMpUser.getCity());
        mpUser.setProvince(wxMpUser.getProvince()==null?"":wxMpUser.getProvince());
        mpUser.setCountry(wxMpUser.getCountry()==null?"":wxMpUser.getCountry());
        mpUser.setHeadimgurl(wxMpUser.getHeadImgUrl()==null?"":wxMpUser.getHeadImgUrl());
        mpUser.setSubscribetime(wxMpUser.getSubscribeTime()==null?"":wxMpUser.getSubscribeTime().toString());
        mpUser.setUnionid(wxMpUser.getUnionId());
        mpUser.setRemark(wxMpUser.getRemark()==null?"":wxMpUser.getRemark());
        mpUser.setGroupid(wxMpUser.getGroupId()==null?"":wxMpUser.getGroupId().toString());
        return mpUser;
    }



}
