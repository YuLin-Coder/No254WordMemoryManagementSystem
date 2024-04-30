package com.ian.media.dao;


import com.ian.media.model.yuangong;
import com.ian.media.model.dingdan;


public interface yuangongMapper extends BaseDao<yuangong, String> {
	/*
    int countByExample(yuangongExample example);

    int deleteByExample(yuangongExample example);

    int insert(yuangong record);

    int insertSelective(yuangong record);

    List<yuangong> selectByExampleWithBLOBs(yuangongExample example);

    List<yuangong> selectByExample(yuangongExample example);

    //int updateByExampleSelective(@Param("record") yuangong record, @Param("example") yuangongExample example);

    int updateByExampleWithBLOBs(@Param("record") yuangong record, @Param("example") yuangongExample example);

    int updateByExample(@Param("record") yuangong record, @Param("example") yuangongExample example);
*/
	public int insertdingdan(dingdan paramT);

}