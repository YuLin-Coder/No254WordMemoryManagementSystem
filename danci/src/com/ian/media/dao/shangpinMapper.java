package com.ian.media.dao;


import com.ian.media.model.shangpin;
import com.ian.media.model.dingdan;


public interface shangpinMapper extends BaseDao<shangpin, String> {
	/*
    int countByExample(caipinExample example);

    int deleteByExample(caipinExample example);

    int insert(caipin record);

    int insertSelective(caipin record);

    List<caipin> selectByExampleWithBLOBs(caipinExample example);

    List<caipin> selectByExample(caipinExample example);

    //int updateByExampleSelective(@Param("record") caipin record, @Param("example") caipinExample example);

    int updateByExampleWithBLOBs(@Param("record") caipin record, @Param("example") caipinExample example);

    int updateByExample(@Param("record") caipin record, @Param("example") caipinExample example);
*/
	public int insertdingdan(dingdan paramT);

}