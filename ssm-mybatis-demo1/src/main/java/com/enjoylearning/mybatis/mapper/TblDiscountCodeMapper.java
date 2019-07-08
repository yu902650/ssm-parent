package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TblDiscountCode;

public interface TblDiscountCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblDiscountCode record);

    int insertSelective(TblDiscountCode record);

    TblDiscountCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblDiscountCode record);

    int updateByPrimaryKey(TblDiscountCode record);
}