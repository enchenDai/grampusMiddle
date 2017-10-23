package com.deepblue.middleware.web.rest;

import com.deepblue.middleware.domian.CurrentPalmset;
import com.deepblue.middleware.service.CurrentPalmsetService;
import com.deepblue.middleware.web.rest.vm.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by enchen on 10/12/17.
 */
@RestController
@RequestMapping("/api/middleware/palmset")
public class CurrentPalmsetResource {

    @Autowired
    CurrentPalmsetService currentPalmsetService;

    /**
     * 创建    current_palmset
     *
     * @param currentPalmset
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<WebResult> saveCurrentPalmset(@RequestBody CurrentPalmset currentPalmset) {
        return ResponseEntity.ok(
                WebResult.ok(
                        currentPalmsetService.saveCurrentPalmset(currentPalmset)
                )
        );
    }

    /**
     * 更新
     *
     * @param currentPalmset
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<WebResult> updateCurrentPalmset(@RequestBody CurrentPalmset currentPalmset) {
        return ResponseEntity.ok(
                WebResult.ok(
                        currentPalmsetService.updateCurrentPalmset(currentPalmset)
                )
        );
    }

    /**
     * 逻辑删除     current_palmset
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<WebResult> removeCurrentPalmset(@PathVariable Long id) {
        return ResponseEntity.ok(
                WebResult.ok(
                        currentPalmsetService.removeCurrentPalmset(id)
                )
        );
    }

    /**
     * 获取current_palmset详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<WebResult> getCurrentPalmsetDetail(@PathVariable Long id) {
        return ResponseEntity.ok(
                WebResult.ok(
                        currentPalmsetService.getCurrentPalmsetDetail(id)
                )
        );
    }

    /**
     * 获取当前可插入的 palmset
     *
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public ResponseEntity<WebResult> getActiveEnableCurrentPalmset() {
        return ResponseEntity.ok(
                WebResult.ok(
                        currentPalmsetService.getActiveEnableCurrentPalmset()
                )
        );
    }
}
