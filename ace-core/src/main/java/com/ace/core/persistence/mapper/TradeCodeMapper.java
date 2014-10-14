package com.ace.core.persistence.mapper;

import com.ace.core.persistence.entity.TradeCode;

public interface TradeCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeCode record);

    int insertSelective(TradeCode record);

    TradeCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeCode record);

    int updateByPrimaryKey(TradeCode record);
}