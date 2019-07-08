package com.ssm.service.Impl;

import com.ssm.dao.IHistoryMapper;
import com.ssm.pojo.HistoryEntity;
import com.ssm.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bobo
 * @auther: bobo_yu
 * @Date: 2018/12/15 14:05
 * @Description:
 * (propagation= Propagation.REQUIRED, rollbackFor=Exception.class) 事务
 */
@Service
@Transactional
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private IHistoryMapper historyMapper;

    @Override
    public void insert(HistoryEntity historyEntity) {
        historyMapper.insert(historyEntity);
    }

    @Override
    public List<HistoryEntity> queryList() {
        return historyMapper.queryList();
    }

    @Override
    public HistoryEntity findObjById(String id) {
        return historyMapper.findObjById(id);
    }
}
