package com.wk.p3.greenmall.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Page {
	/*
	 * 总条数
	 */
	private int count;
	/*
	 * 展示页面
	 */
	@SuppressWarnings("unused")
	private List<Integer>listPageNum;
	/*
	 * 每页展示条数
	 */
	private int pageSize;
	/*
	 * 总页数
	 */
	private int allPageCount;
	/*
	 * 当前pageNum
	 */
	private int currentPageNum;
	/*
	 * 上一页
	 */
	@SuppressWarnings("unused")
	private int frontPageNum;
	
	/*
	 * 下一页
	 */
	@SuppressWarnings("unused")
	private int nextPageNum;

	public Page(int count,int pagesize,int currentPage){
		this.count=count;
		this.pageSize=pagesize;
		this.currentPageNum=currentPage;
		this.listPageNum=getListPageNum();
		this.frontPageNum=getFrontPageNum();
		this.nextPageNum=getNextPageNum();
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Integer> getListPageNum() {
		List<Integer>list=new ArrayList<Integer>();
		if(this.currentPageNum<3){
			int pagecount=this.getAllPageCount();
			if(this.allPageCount>5){
				for(int i=0;i<5;i++){
					list.add(i+1);
				}
			}else{
				for(int i=0;i<pagecount;i++){
					list.add(i+1);
				}
			}
		}else if(this.currentPageNum>=3){
			int pagecount=this.getAllPageCount();
			if(pagecount+1>this.currentPageNum+2){
				for(int i=this.currentPageNum-3;i<this.currentPageNum+2;i++){
					list.add(i+1);
				}
			}else{
				for(int i=this.getAllPageCount()-5;i<this.getAllPageCount();i++){
					list.add(i+1);
				}
			}
		}
		return list;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getAllPageCount() {
		allPageCount=count % pageSize==0 ? count/pageSize : count/pageSize+1;
		return allPageCount;
	}
	public void setListPageNum(List<Integer> listPageNum) {
		this.listPageNum = listPageNum;
	}
	
	public int getFrontPageNum() {
		return this.currentPageNum==1 ? 1:this.currentPageNum-1;
	}
	public int getNextPageNum() {
		return this.currentPageNum==this.allPageCount?this.currentPageNum:this.currentPageNum+1;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public static void main(String[] args) {
		System.out.println(JSONObject.toJSON(new Page(120,10,6)));
		System.out.println(JSONObject.toJSON(new Page(120,10,5)));
		System.out.println(JSONObject.toJSON(new Page(120,10,11)));
		System.out.println(JSONObject.toJSON(new Page(120,10,12)));
		System.out.println(JSONObject.toJSON(new Page(120,10,1)));
	}
	
	
	
	
	
	
}
