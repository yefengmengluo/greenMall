package com.wk.p3.greenmall.modules.search.entry;

public enum SearchType {
	
	SUPPLY(1),BUYERS(2),LIMIT(3);
	
	private SearchType(Integer i){
		this.type=i;
	} 
	public String toString(){
		return type.toString();
	}
	private Integer type;

}
