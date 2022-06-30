package com.example.hellodocker.mapper;

import com.example.hellodocker.bean.PowerUser;

public interface PowerUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(PowerUser record);

    int insertSelective(PowerUser record);

    PowerUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(PowerUser record);

    int updateByPrimaryKey(PowerUser record);

}