package com.excalibur.znovel.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

public class JPushUtil {

    public static void sendOfflineMessage(String resID){
        JPushClient jpushClient = new JPushClient("","");
        try {
            jpushClient.sendAndroidMessageWithRegistrationID("","offline",resID);
        } catch (APIConnectionException | APIRequestException e) {
            e.printStackTrace();
        }
    }

}
