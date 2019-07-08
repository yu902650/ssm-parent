package com.ssm.dao;

import com.ssm.pojo.HistoryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/15 14:13
 * @Description:
 */
@Repository
public interface IHistoryMapper {

    /**
     * 保存
     * @param historyEntity
     */
    void insert(HistoryEntity historyEntity);

    List<HistoryEntity> queryList();

    HistoryEntity findObjById(@Param("id") String id);

}
