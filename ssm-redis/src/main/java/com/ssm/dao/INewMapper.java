package com.ssm.dao;

import com.ssm.pojo.News;
import org.springframework.stereotype.Repository;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/20 17:16
 * @Description:
 */
@Repository
public interface INewMapper  {

    void addNews(News news);

}
