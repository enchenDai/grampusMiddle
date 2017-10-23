package com.deepblue.middleware.service;

import com.alibaba.fastjson.JSON;
import com.deepblue.middleware.config.Constants;
import com.deepblue.middleware.domian.CurrentPalmset;
import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.service.dto.*;
import com.deepblue.middleware.service.enums.PalmTypeEnum;
import com.deepblue.middleware.service.enums.PalmsetNotifyEnum;
import com.deepblue.middleware.service.util.JsonUtil;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * Created by enchen on 10/10/17.
 */
@Service
public class GrampusService {

    @Autowired
    GrampusClientService grampusClientService;

    @Autowired
    CurrentPalmsetService currentPalmsetService;

    /**
     * 操作掌脉（注册,更新,删除）
     * registerPostMessage   type      "1": 注册  "2": 更新  "3": 删除
     *
     * @param registerPostMessage
     * @return
     */
    public String operationPalm(RegisterPostMessage registerPostMessage) {
        checkPalmOperation(registerPostMessage);
        String result = null;
        if (registerPostMessage.getType().getTypeCode().equals(PalmTypeEnum.ADD_TYPE.getTypeCode())) {
            //创建掌脉
            result = grampusClientService.operationPalm(registerPostMessage);
            //查看 可插入掌脉的掌脉集
            CurrentPalmset activeCurrentPalmset = currentPalmsetService.getActiveEnableCurrentPalmset();
            if (null == activeCurrentPalmset) {
                //创建掌脉集
                PalmsetPostMessage palmsetPostMessage = new PalmsetPostMessage();
                palmsetPostMessage.setPalmsetName(Constants.PALMSET_NAME_PREFIX + "1");
                palmsetPostMessage.setCount(Constants.PALMSET_MAX_N_VALUE);
                insertPalmset(palmsetPostMessage);
                // 关联掌脉集
                PalmsetNotifyPostMessage palmsetNotifyPostMessage = new PalmsetNotifyPostMessage();
                palmsetNotifyPostMessage.setPalmsetName(palmsetPostMessage.getPalmsetName());
                palmsetNotifyPostMessage.setUserIdList(Arrays.asList(new String[]{registerPostMessage.getUserId()}));
                palmsetNotifyPostMessage.setNotify(PalmsetNotifyEnum.ADD_TYPE);
                palmsetNotify(palmsetNotifyPostMessage);
                //创建 current palmset 记录
                CurrentPalmset currentPalmset = new CurrentPalmset();
                currentPalmset.setPalmsetName(palmsetPostMessage.getPalmsetName());
                currentPalmset.setCount(1);
                currentPalmsetService.saveCurrentPalmset(currentPalmset);
            } else {
                if (activeCurrentPalmset.getCount() > Integer.valueOf(Constants.PALMSET_MAX_N_VALUE)) {
                    //掌脉集人数超过 max value ,创建新掌脉集
                    String[] splitName = activeCurrentPalmset.getPalmsetName().split("_");
                    Integer nameCode = Integer.valueOf(splitName[splitName.length - 1]) + 1;
                    //创建掌脉集
                    PalmsetPostMessage palmsetPostMessage = new PalmsetPostMessage();
                    palmsetPostMessage.setPalmsetName(Constants.PALMSET_NAME_PREFIX + nameCode);
                    palmsetPostMessage.setCount(Constants.PALMSET_MAX_N_VALUE);
                    insertPalmset(palmsetPostMessage);
                    // 关联掌脉集
                    PalmsetNotifyPostMessage palmsetNotifyPostMessage = new PalmsetNotifyPostMessage();
                    palmsetNotifyPostMessage.setPalmsetName(palmsetPostMessage.getPalmsetName());
                    palmsetNotifyPostMessage.setUserIdList(Arrays.asList(new String[]{registerPostMessage.getUserId()}));
                    palmsetNotifyPostMessage.setNotify(PalmsetNotifyEnum.ADD_TYPE);
                    palmsetNotify(palmsetNotifyPostMessage);
                    //创建 current palmset 记录
                    CurrentPalmset currentPalmset = new CurrentPalmset();
                    currentPalmset.setPalmsetName(palmsetPostMessage.getPalmsetName());
                    currentPalmset.setCount(1);
                    currentPalmsetService.saveCurrentPalmset(currentPalmset);
                } else {
                    // 关联掌脉集
                    PalmsetNotifyPostMessage palmsetNotifyPostMessage = new PalmsetNotifyPostMessage();
                    palmsetNotifyPostMessage.setPalmsetName(activeCurrentPalmset.getPalmsetName());
                    palmsetNotifyPostMessage.setUserIdList(Arrays.asList(new String[]{registerPostMessage.getUserId()}));
                    palmsetNotifyPostMessage.setNotify(PalmsetNotifyEnum.ADD_TYPE);
                    palmsetNotify(palmsetNotifyPostMessage);
                    //获取掌脉集信息
                    PalmsetDetailDTO palmsetDetailDTO = getPalmsetDetail(activeCurrentPalmset.getPalmsetName());
                    //更新 current palmset 记录
                    activeCurrentPalmset.setCount(Integer.valueOf(palmsetDetailDTO.getPalmRes().getN()));
                    currentPalmsetService.updateCurrentPalmset(activeCurrentPalmset);
                }
            }

        } else {
            result = grampusClientService.operationPalm(registerPostMessage);
        }
        return result;
    }

    /**
     * 创建掌脉集
     *
     * @param palmsetPostMessage
     * @return
     */
    public String insertPalmset(PalmsetPostMessage palmsetPostMessage) {
        checkPalmSetOperation(palmsetPostMessage);
        return grampusClientService.insertPalmset(palmsetPostMessage);
    }

    /**
     * 更新掌脉集
     *
     * @param palmsetPostMessage
     * @return
     */
    public String updatePalmset(PalmsetPostMessage palmsetPostMessage) {
        checkPalmSetOperation(palmsetPostMessage);
        return grampusClientService.updatePalmset(palmsetPostMessage);
    }

    /**
     * 获取掌脉集信息
     *
     * @param palmsetName
     * @return
     */
    public PalmsetDetailDTO getPalmsetDetail(String palmsetName) {
        PalmsetPostMessage palmsetPostMessage = new PalmsetPostMessage();
        palmsetPostMessage.setPalmsetName(palmsetName);
        String result = grampusClientService.getPalmsetDetail(palmsetPostMessage);
        PalmsetDetailDTO palmsetDetailDTO = JSON.parseObject(result, PalmsetDetailDTO.class);
        return palmsetDetailDTO;
    }

    /**
     * palmset 批量追加/删除 人员信息
     *
     * @param palmsetNotifyPostMessage
     * @return
     */
    public String palmsetNotify(PalmsetNotifyPostMessage palmsetNotifyPostMessage) {
        checkPalmSetNotify(palmsetNotifyPostMessage);
        return grampusClientService.palmsetNotify(palmsetNotifyPostMessage);
    }

    /**
     * 1:1 识别
     *
     * @param verifyPostMessage
     * @return
     */
    public String verifyPalm(VerifyPostMessage verifyPostMessage) {
        checkVerifyPalmRequired(verifyPostMessage);
        String result = grampusClientService.verifyPalm(verifyPostMessage);
        return result;
    }

    /**
     * 1:N 认证
     *
     * @param identifyPostMessage
     * @return
     */
    public String identifyPalm(IdentifyPostMessage identifyPostMessage) {
        checkIdentifyPalmRequired(identifyPostMessage);
        String result = grampusClientService.identifyPalm(identifyPostMessage);
        return result;
    }

    /**
     * 轮训调用 1:N 比对
     *
     * @param capture
     * @return
     */
    public String pollingIdentifyPalm(String capture) {
        if(null == capture){
            throw new SystemException(ResultCode.PALM_CAPTURE_REQUIRED, "Palm Verify Capture Required");
        }
        List<String> palmsetNameList = getlistPalmsetName();
        Collections.shuffle(palmsetNameList);
        System.out.println(palmsetNameList);
        for (String palmsetName : palmsetNameList) {
            IdentifyPostMessage identifyPostMessage = new IdentifyPostMessage();
            identifyPostMessage.setPalmsetName(palmsetName);
            identifyPostMessage.setCapture(capture);
            String result = grampusClientService.pollingIdentifyPalm(identifyPostMessage);
            System.out.println(result);
            if (null != result) {
                return result;
            }
        }
        return null;
    }

    /**
     * 查询 userId 在掌脉系统中是否存在
     *
     * @param queryPostMessage
     * @return
     */
    public String personValidQuery(QueryPostMessage queryPostMessage) {
        checkUserIdRequired(queryPostMessage);
        String result = grampusClientService.personValidQuery(queryPostMessage);
        return result;
    }

    /**
     * 查询所有 palmset 名称的集合
     *
     * @return
     */
    public List<String> getlistPalmsetName() {
        String result = grampusClientService.getlistPalmsetName();
        PalmsetNameListDTO palmsetNameListDTO = JsonUtil.fromJson(result, PalmsetNameListDTO.class);
        if (null == palmsetNameListDTO.getPalmRes().getPalmsetNameList()) {
            return new ArrayList<>();
        }
        return palmsetNameListDTO.getPalmRes().getPalmsetNameList();
    }

    /**
     * 掌脉操作必填校验
     *
     * @param registerPostMessage
     */
    private void checkPalmOperation(RegisterPostMessage registerPostMessage) {
        if (null == registerPostMessage.getType()) {
            throw new SystemException(ResultCode.PALM_TYPE_OPERATION_ERROR, "Palm Type Operation Error");
        }
        if (null == registerPostMessage.getUserId()) {
            throw new SystemException(ResultCode.USER_ID_REQUIRED, "User ID Required");
        }
        if (!registerPostMessage.getType().equals(PalmTypeEnum.DELETE_TYPE)) {
            if (null == registerPostMessage.getVein()) {
                throw new SystemException(ResultCode.VEIN_REQUIRED, "Vein Required");
            }
        }
    }

    /**
     * 掌脉集操作必填校验
     *
     * @param palmsetPostMessage
     */
    private void checkPalmSetOperation(PalmsetPostMessage palmsetPostMessage) {
        if (null == palmsetPostMessage.getPalmsetName()) {
            throw new SystemException(ResultCode.PALMSET_NAME_REQUIRED, "Palmset Name Required");
        }
        if (null == palmsetPostMessage.getCount()) {
            throw new SystemException(ResultCode.PALMSET_COUNT_REQUIRED, "Palmset Count Required");
        }
    }

    /**
     * palmset 批量追加/删除 人员信息 必填校验
     *
     * @param palmsetNotifyPostMessage
     */
    private void checkPalmSetNotify(PalmsetNotifyPostMessage palmsetNotifyPostMessage) {
        if (null == palmsetNotifyPostMessage.getPalmsetName()) {
            throw new SystemException(ResultCode.PALMSET_NAME_REQUIRED, "Palmset Name Required");
        }
        if (null == palmsetNotifyPostMessage.getNotify()) {
            throw new SystemException(ResultCode.PALMSET_PERSON_NOTIFY_TYPE_REQUIRED, "Palmset Person Association Notify Type Required");
        }
        if (CollectionUtils.isEmpty(palmsetNotifyPostMessage.getUserIdList())) {
            throw new SystemException(ResultCode.PALMSET_PERSION_NOTIFY_USER_ID_LIST_NOT_EMPTY, "Palmset Person Association User ID List Not Empty");
        }
    }

    /**
     * 1:1 识别 必填校验
     *
     * @param verifyPostMessage
     */
    private void checkVerifyPalmRequired(VerifyPostMessage verifyPostMessage) {
        if (null == verifyPostMessage.getUserId()) {
            throw new SystemException(ResultCode.USER_ID_REQUIRED, "User ID Required");
        }
        if (null == verifyPostMessage.getCapture()) {
            throw new SystemException(ResultCode.PALM_CAPTURE_REQUIRED, "Palm Verify Capture Required");
        }
    }

    /**
     * 1:N 认证 必填校验
     *
     * @param identifyPostMessage
     */
    private void checkIdentifyPalmRequired(IdentifyPostMessage identifyPostMessage) {
        if (null == identifyPostMessage.getPalmsetName()) {
            throw new SystemException(ResultCode.PALMSET_NAME_REQUIRED, "Palmset Name Required");
        }
        if (null == identifyPostMessage.getCapture()) {
            throw new SystemException(ResultCode.PALM_CAPTURE_REQUIRED, "Palm Verify Capture Required");
        }
    }

    /**
     * 用户id 是否在掌脉注册     userId 必填校验
     *
     * @param queryPostMessage
     */
    private void checkUserIdRequired(QueryPostMessage queryPostMessage) {
        if (null == queryPostMessage.getUserId()) {
            throw new SystemException(ResultCode.USER_ID_REQUIRED, "User ID Required");
        }
    }
}
