package com.deepblue.middleware.web.rest;

import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.service.GrampusService;
import com.deepblue.middleware.service.dto.*;
import com.deepblue.middleware.service.enums.PalmTypeEnum;
import com.deepblue.middleware.service.enums.PalmsetNotifyEnum;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import com.deepblue.middleware.web.rest.vm.WebResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by enchen on 10/10/17.
 */
@RestController
@RequestMapping("/api/grampus")
public class GrampusResource {

    @Autowired
    GrampusService grampusService;

    /**
     * 创建/更新/删除 掌脉信息
     *
     * @param palmDTO
     * @return
     */
    @RequestMapping(value = "/palm/operation", method = RequestMethod.POST)
    public ResponseEntity<WebResult> personRegister(@RequestBody PalmDTO palmDTO) {


        RegisterPostMessage registerPostMessage = new RegisterPostMessage();
        registerPostMessage.setType(PalmTypeEnum.getEnumByCode(palmDTO.getType()));
        registerPostMessage.setUserId(palmDTO.getUserId());
        registerPostMessage.setVein(palmDTO.getVein());
        grampusService.operationPalm(registerPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 添加掌脉集
     *
     * @return
     */
    @RequestMapping(value = "/palmset/insert", method = RequestMethod.POST)
    public ResponseEntity<WebResult> insertPalmset(@RequestBody PalmsetDTO palmsetDTO) {
        PalmsetPostMessage palmsetPostMessage = new PalmsetPostMessage();
        BeanUtils.copyProperties(palmsetDTO, palmsetPostMessage);
        grampusService.insertPalmset(palmsetPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 更新掌脉集
     *
     * @return
     */
    @RequestMapping(value = "/palmset/update", method = RequestMethod.PUT)
    public ResponseEntity<WebResult> updatePalmset(@RequestBody PalmsetDTO palmsetDTO) {
        PalmsetPostMessage palmsetPostMessage = new PalmsetPostMessage();
        BeanUtils.copyProperties(palmsetDTO, palmsetPostMessage);
        grampusService.updatePalmset(palmsetPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 获取掌脉集信息
     *
     * @return
     */
    @RequestMapping(value = "/palmset/detail", method = RequestMethod.POST)
    public ResponseEntity<WebResult> updatePalmset(@RequestBody Map<String, String> map) {
        String palmsetName = map.get("palmsetName");
        if (null == palmsetName) {
            throw new SystemException(ResultCode.PALMSET_NAME_REQUIRED, "Palmset Name Required");
        }
        return ResponseEntity.ok(
                WebResult.ok(
                        grampusService.getPalmsetDetail(palmsetName)
                )
        );
    }

    /**
     * 轮训调用 1:N 比对
     *
     * @param identifyPalmDTO
     * @return
     */
    @RequestMapping(value = "/palm/polling/identify", method = RequestMethod.POST)
    public ResponseEntity<WebResult> pollingIdentifyPalm(@RequestBody IdentifyPalmDTO identifyPalmDTO) {
        String capture = identifyPalmDTO.getCapture();
        String result = grampusService.pollingIdentifyPalm(capture);
        if (null == result) {
            return ResponseEntity.ok(
                    new WebResult(ResultCode.USER_NO_ASSOCIATION_WITH_ALL_PALMSET, "user no association with all palmset", null)
            );
        } else {
            return ResponseEntity.ok(
                    WebResult.ok()
            );
        }
    }

    /**
     * palmset 批量追加/删除 人员信息
     *
     * @return
     */
    @RequestMapping(value = "/palmset/notify", method = RequestMethod.POST)
    public ResponseEntity<WebResult> updatePalmset(@RequestBody PalmsetNotifyDTO palmsetNotifyDTO) {
        PalmsetNotifyPostMessage palmsetNotifyPostMessage = new PalmsetNotifyPostMessage();
        palmsetNotifyPostMessage.setPalmsetName(palmsetNotifyDTO.getPalmsetName());
        palmsetNotifyPostMessage.setUserIdList(palmsetNotifyDTO.getUserIdList());
        palmsetNotifyPostMessage.setNotify(PalmsetNotifyEnum.getEnumByCode(palmsetNotifyDTO.getNotify()));
        grampusService.palmsetNotify(palmsetNotifyPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 掌脉 1:1 识别
     *
     * @param verifyPalmDTO
     * @return
     */
    @RequestMapping(value = "/palm/verify", method = RequestMethod.POST)
    public ResponseEntity<WebResult> verifyPalm(@RequestBody VerifyPalmDTO verifyPalmDTO) {
        VerifyPostMessage verifyPostMessage = new VerifyPostMessage();
        BeanUtils.copyProperties(verifyPalmDTO, verifyPostMessage);
        grampusService.verifyPalm(verifyPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 掌脉 1:N 认证
     *
     * @param identifyPalmDTO
     * @return
     */
    @RequestMapping(value = "/palm/identify", method = RequestMethod.POST)
    public ResponseEntity<WebResult> verifyPalm(@RequestBody IdentifyPalmDTO identifyPalmDTO) {
        IdentifyPostMessage identifyPostMessage = new IdentifyPostMessage();
        BeanUtils.copyProperties(identifyPalmDTO, identifyPostMessage);
        String result = grampusService.identifyPalm(identifyPostMessage);
        if (null == result) {
            return ResponseEntity.ok(
                    new WebResult(ResultCode.HTTP_MISS, "http no response", null)
            );
        } else {
            return ResponseEntity.ok(
                    WebResult.ok()
            );
        }
    }

    /**
     * 查询 userId 在掌脉系统中是否存在
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/person/valid", method = RequestMethod.GET)
    public ResponseEntity<WebResult> personValidQuery(@RequestParam String userId) {
        QueryPostMessage queryPostMessage = new QueryPostMessage();
        queryPostMessage.setUserId(userId);
        grampusService.personValidQuery(queryPostMessage);
        return ResponseEntity.ok(
                WebResult.ok()
        );
    }

    /**
     * 查询所有 palmset 名称的集合
     *
     * @return
     */
    @RequestMapping(value = "/list/palmsetName", method = RequestMethod.GET)
    public ResponseEntity<WebResult> getlistPalmsetName() {
        return ResponseEntity.ok(
                WebResult.ok(
                        grampusService.getlistPalmsetName()
                )
        );
    }
}
