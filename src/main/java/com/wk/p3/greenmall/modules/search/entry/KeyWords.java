package com.wk.p3.greenmall.modules.search.entry;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @author Robin
 *
 */
public class KeyWords {
    
	@Field("id")
	private String id;
	//关键词
	@Field("kw")
	private String keyword;
	//拼音
	@Field("pinyin")
	private String pinyin;
	//触发频率
	@Field("kwfreq")
	private Integer kwfreq;
	@Field("firstspell")
	private String firstspell;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public Integer getKwfreq() {
		return kwfreq;
	}
	public void setKwfreq(Integer kwfreq) {
		this.kwfreq = kwfreq;
	}
	public String getFirstspell() {
		return firstspell;
	}
	public void setFirstspell(String firstspell) {
		this.firstspell = firstspell;
	}
	
	
	
	
}
