package com.ian.media.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ian.media.model.Link;

public interface LinkMapper extends BaseDao<Link, String>{

	List<LinkMapper> getLinkList(@Param("schoolId") int schoolId);

}
