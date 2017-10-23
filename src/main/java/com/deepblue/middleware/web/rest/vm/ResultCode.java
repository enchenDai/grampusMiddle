package com.deepblue.middleware.web.rest.vm;

/**
 * Created by enchen on 2017/5/24.
 */
public final class ResultCode {

    /**
     * exception message id , 与 i18n 文件中 key 对应
     */

    public static final String UNKNOW_ERROR = "UNKNOW ERROR";

    public static final String UNKNOW_SOURCE = "UNKNOW SOURCE";

    //请求成功
    public static final String SUCCESS = "1";

    public static final String NOT_FOUND = "404";    //找不到对象

    public static final String FORBIDDEN = "403";   //无权访问该接口

    public static final String NOT_ALLOWED = "405";     //无法访问该方法

    public static final String ADD_SUCCESS = "201";  //添加成功

    public static final String EDIT_SUCCESS = "202"; //修改成功

    public static final String DELETE_SUCCESS = "203";   //删除成功

    //http 请求未响应
    public static final String HTTP_MISS = "3001";      //http 请求未响应

    //系统错误
    public static final String SYSTEM_ERROR = "5001";       //系统内部错误

    //掌脉请求错误
    public static final String PALM_ERROR = "5002";       //掌脉错误

    //掌脉集请求错误
    public static final String PALM_SET_ERROR = "5003";       //掌脉集错误

    //palmset 批量追加/删除 人员信息 请求错误
    public static final String PALMSET_PERSON_OPERATION_ERROR = "5004";       //palmset 批量追加/删除 人员信息

    //掌脉1:1识别请求错误
    public static final String PALM_VERIFY_ERROR = "5005";       //掌脉1:1识别错误

    //掌脉1:N认证请求错误
    public static final String PALM_IDENTIFY_ERROR = "5006";       //掌脉1:N认证错误

    public static final String USER_ID_EXIST_VALID_ERROR = "5007";       //检查用户 id 在掌脉系统中是否存在 错误

    //所有 palmset 中没有用户关联信息
    public static final String USER_NO_ASSOCIATION_WITH_ALL_PALMSET = "5008";   //所有 palmset 中没有用户关联信息

    //个人掌脉操作
    public static final String PALM_TYPE_OPERATION_ERROR = "6001";       //掌脉操作类型有误
    public static final String USER_ID_REQUIRED = "6002";       //用户唯一 ID 必填
    public static final String VEIN_REQUIRED = "6003";       //掌脉采集数据必填

    //掌脉集操作
    public static final String PALMSET_NAME_REQUIRED = "7001";       //掌脉集名称必填
    public static final String PALMSET_COUNT_REQUIRED = "7002";       //掌脉集容量必填
    public static final String PALMSET_ID_REQUIRED = "7002";       //掌脉集ID不能为空

    //palmset 批量追加/删除 人员信息
    public static final String PALMSET_PERSON_NOTIFY_TYPE_REQUIRED = "8001";       //palmset 批量追加/删除 人员信息 类型必填
    public static final String PALMSET_PERSION_NOTIFY_USER_ID_LIST_NOT_EMPTY = "8002";       //用户 id 列表不能为空

    //掌脉对比
    public static final String PALM_CAPTURE_REQUIRED = "9001";       //识别、比对 时掌脉必填
}
