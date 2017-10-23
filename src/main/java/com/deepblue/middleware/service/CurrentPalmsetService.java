package com.deepblue.middleware.service;

import com.deepblue.middleware.domian.CurrentPalmset;
import com.deepblue.middleware.exception.ObjectNotFoundException;
import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.repository.middleware.CurrentPalmsetRepository;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by enchen on 10/12/17.
 */
@Service
public class CurrentPalmsetService {

    @Autowired
    CurrentPalmsetRepository currentPalmsetRepository;

    /**
     * 创建 currentPalmset 记录
     *
     * @param currentPalmset
     * @return
     */
    @Transactional
    public CurrentPalmset saveCurrentPalmset(CurrentPalmset currentPalmset) {
        checkRequired(currentPalmset);
        currentPalmset.setEnable(1);
        currentPalmset.setCreateDate(new Date());
        CurrentPalmset exist = getActiveEnableCurrentPalmset();
        if(null != exist){
            exist.setEnable(0);
            currentPalmsetRepository.save(exist);
        }
        return currentPalmsetRepository.save(currentPalmset);
    }

    /**
     * 更新 currentPalmset 记录
     *
     * @param currentPalmset
     * @return
     */
    @Transactional
    public CurrentPalmset updateCurrentPalmset(CurrentPalmset currentPalmset) {
        if (currentPalmset.getId() == null) {
            throw new SystemException(ResultCode.PALMSET_ID_REQUIRED, "Palmset Id can't be null");
        }
        CurrentPalmset exist = getCurrentPalmsetDetail(currentPalmset.getId());
        BeanUtils.copyProperties(currentPalmset, exist);
        exist.setUpdateDate(new Date());
        return currentPalmsetRepository.save(exist);
    }

    /**
     * 删除 currentPalmset 记录
     *
     * @param id
     * @return
     */
    @Transactional
    public CurrentPalmset removeCurrentPalmset(Long id) {
        CurrentPalmset currentPalmset = getCurrentPalmsetDetail(id);
        currentPalmset.setEnable(0);
        return currentPalmsetRepository.save(currentPalmset);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    public CurrentPalmset getCurrentPalmsetDetail(Long id) {
        CurrentPalmset exist = currentPalmsetRepository.findOne(id);
        if (null == exist) {
            throw new ObjectNotFoundException(CurrentPalmset.class, id);
        }
        return exist;
    }

    /**
     * 获取激活的 palmset 记录
     *
     * @return
     */
    @Transactional(readOnly = true)
    public CurrentPalmset getActiveEnableCurrentPalmset() {
        List<CurrentPalmset> currentPalmsetList = currentPalmsetRepository.findByEnable(1);
        if (CollectionUtils.isEmpty(currentPalmsetList)) {
            return null;
        } else {
            return currentPalmsetList.get(0);
        }
    }

    private void checkRequired(CurrentPalmset currentPalmset) {
        if (null == currentPalmset.getPalmsetName()) {
            throw new SystemException(ResultCode.PALMSET_NAME_REQUIRED, "Palmset Name Required");
        }
        if (null == currentPalmset.getCount()) {
            throw new SystemException(ResultCode.PALMSET_COUNT_REQUIRED, "Palmset Count Required");
        }
    }
}
