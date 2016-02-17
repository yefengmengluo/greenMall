package com.wk.p3.greenmall.modules.paycenter;

/**
 * 
 * @author Robin
 * 支付状态
 *
 */
public enum PayStatus {

	
	SUCCESS(1),FAIL(2);
	
	PayStatus(Integer status){
		this.status=status;
	}
	public String toString(){
		return this.status.toString();
	}
	private Integer status;
	
}
