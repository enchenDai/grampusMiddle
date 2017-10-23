package com.deepblue.middleware.service.util;

/**
 * Created by enchen on 10/12/17.
 */
public class ErrorCodeHandler {

    public static String errorCodeConvert(String code){
        String message = null;
        switch (code){
            case "PALMYUN1001":
                message = "数据库操作异常";
                break;
            case "PALMYUN1002":
                message = "IO流读写异常";
                break;
            case "PALMYUN1003":
                message = "JSON解析异常";
                break;
            case "PALMYUN1004":
                message = "唯一应用APP_ID不能为空";
                break;
            case "PALMYUN1005":
                message = "REDIS操作异常";
                break;
            case "PALMYUN1006":
                message = "认证失败";
                break;
            case "PALMYUN2001":
                message = "该会员已经注册";
                break;
            case "PALMYUN2002":
                message = "业务系统USER_ID不能为空";
                break;
            case "PALMYUN2003":
                message = "会员信息未查询到(未创建注册)";
                break;
            case "PALMYUN2004":
                message = "掌静脉信息为空或采集不准确";
                break;
            case "PALMYUN3001":
                message = "PalmsetName不能为空";
                break;
            case "PALMYUN3002":
                message = "PalmsetName不能相同";
                break;
            case "PALMYUN3003":
                message = "Palmset未查询到";
                break;
            case "PALMYUN3004":
                message = "Palmset追加User为空";
                break;
            case "PALMYUN3005":
                message = "Palmset追加的User还没有注册成为Person";
                break;
            case "PALMYUN3006":
                message = "Palmset下删除User为空";
                break;
            case "PALMYUN3007":
                message = "Palmset删除的User还没有注册成为Person";
                break;
            case "PALMYUN3008":
                message = "设置Palmset信息的Tag不能为空";
                break;
            case "PALMYUN3009":
                message = "设置Palmset信息的N值不能为空";
                break;
            case "PALMYUN4001":
                message = "Capture数据为空或采集不准确";
                break;
            default:
                message = code;
        }
        return message;
    }
}
