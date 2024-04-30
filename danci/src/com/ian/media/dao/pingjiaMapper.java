package com.ian.media.dao;


import com.ian.media.model.pingjia;
import com.ian.media.model.dingdan;


public interface pingjiaMapper extends BaseDao<pingjia, String> {
	/*
    int countByExample(pingjiaExample example);

    int deleteByExample(pingjiaExample example);

    int insert(pingjia record);

    int insertSelective(pingjia record);

    List<pingjia> selectByExampleWithBLOBs(pingjiaExample example);

    List<pingjia> selectByExample(pingjiaExample example);

    //int updateByExampleSelective(@Param("record") pingjia record, @Param("example") pingjiaExample example);

    int updateByExampleWithBLOBs(@Param("record") pingjia record, @Param("example") pingjiaExample example);

    int updateByExample(@Param("record") pingjia record, @Param("example") pingjiaExample example);
*/
	public int insertdingdan(dingdan paramT);

}