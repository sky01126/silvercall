package com.silvercall.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.silvercall.persistence.model.User;

@Mapper
public interface UserMapper {

	User findByUserNo(long userNo);

	User findByUserId(String userId);

}
