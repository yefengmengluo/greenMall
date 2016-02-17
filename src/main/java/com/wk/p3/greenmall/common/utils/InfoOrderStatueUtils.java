package com.wk.p3.greenmall.common.utils;

/**
 * 订单和供求信息的状态 工具类
 * Created by liujiabao on 2016/1/8 0008.
 */
public class InfoOrderStatueUtils {

//   orderStatue     -1表示订单软删除，0表示洽谈中，2表示订单确认，3表示等待付款，4表示等待发货，5表示交易完成
    public static int orderDelete=-1;
    public static int orderTalking=0;
    public static int orderConfirm=2;
    public static int orderWaitingMoney=3;
    public static int orderWaitingDeliver=4;
    public static int orderTradeSuccess=5;
//  infoStatue  状态(默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功)
    public static int waitingConfirm = 0;
    public static int infoConfirm = 1;
    public static int infoDelete=-2;
    public static int infoTalking=2;
    public static int infoWaitingMoney=3;
    public static int infoTradeSuccess=4;

    public static int getInfoStatueByOrderStatue(int orderStatue,double errand){
        int infoStatue=0;

        switch (orderStatue){
            case -1:
                infoStatue = 1;
                break;
            case 0:
                infoStatue = 2;
                break;
            case 2:
                infoStatue = 2;
                break;
            case 3:
                infoStatue = 3;
                break;
            case 4:
                infoStatue = 3;
                break;
            case 5:
                infoStatue = 1;
                break;
        }

        if(errand==0.0){
            infoStatue = -2;
        }
        return infoStatue;
    }

}
