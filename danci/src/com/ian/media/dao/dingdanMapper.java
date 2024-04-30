package com.ian.media.dao;


import com.ian.media.model.dingdan;
import com.ian.media.model.shangpin;



public interface dingdanMapper extends BaseDao<dingdan, String> {
	/*
    int countByExample(dingdanExample example);

    int deleteByExample(dingdanExample example);

    int insert(dingdan record);

    int insertSelective(dingdan record);

    List<dingdan> selectByExampleWithBLOBs(dingdanExample example);

    List<dingdan> selectByExample(dingdanExample example);

    //int updateByExampleSelective(@Param("record") dingdan record, @Param("example") dingdanExample example);

    int updateByExampleWithBLOBs(@Param("record") dingdan record, @Param("example") dingdanExample example);

    int updateByExample(@Param("record") dingdan record, @Param("example") dingdanExample example);
*/
	public void updatecheliang(shangpin paramT);
}