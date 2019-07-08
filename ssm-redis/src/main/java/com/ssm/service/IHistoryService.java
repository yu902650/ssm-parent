package com.ssm.service;

import com.ssm.pojo.HistoryEntity;

import java.util.List;

/**
 * @author bobo
 * @auther: bobo_yu
 * @Date: 2018/12/15 14:05
 * @Description:
 */
public interface IHistoryService {

  void insert(HistoryEntity historyEntity);

  List<HistoryEntity> queryList();

  HistoryEntity findObjById(String id);

}
