package com.ian.media.util;
/*
 * 分页查询
 */
public class PageBean {
	private int allCount=0;
	private int totalPage=1;
	private int currentPage=1;
	private int pageSize=20;
	private int starSize=0;
	
	public int getStarSize() {
		
		return this.starSize=this.pageSize*this.currentPage;
	}
	public void setStarSize(int starSize) {
		this.starSize = starSize;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getTotalPage() {
		int totalPage=allCount%pageSize==0?allCount/pageSize:allCount/pageSize+1;
		if (totalPage==0) {
			totalPage=1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage+1;
	}
	public void setCurrentPage(int currentPage) {
		
		if(currentPage<=0){
			this.currentPage=0;
		}else if(currentPage>this.getTotalPage()){
			this.currentPage=this.getTotalPage()-1;
		}else{
			this.currentPage = currentPage-1;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
