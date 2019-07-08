package com.ssm.service.Impl;

import com.ssm.dao.INewMapper;
import com.ssm.pojo.News;
import com.ssm.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/20 17:15
 * @Description:
 */
@Service
@Transactional
public class NewService implements INewService {

    @Autowired
    private INewMapper newMapper;

    @Override
    public void addNews(News news) {
        newMapper.addNews(news);
    }
}
