package com.ian.media.dao;

import java.util.List;

import com.ian.media.model.PowerRoleUser;

public interface PowerRoleUserMapper  extends BaseDao<PowerRoleUser, String>{
    int deleteByPrimaryUserId(String userId);
    List<PowerRoleUser> selectByUsreId(String userId);
}