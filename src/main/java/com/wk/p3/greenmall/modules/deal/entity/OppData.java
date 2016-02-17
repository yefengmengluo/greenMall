package com.wk.p3.greenmall.modules.deal.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 
 * @author lf
 *
 */
public class OppData extends DataEntity<OppData> {
	private static final long serialVersionUID = 1L;
	private SupplyDemandInfo supplyDemandInfo;

	public SupplyDemandInfo getSupplyDemandInfo() {
		return supplyDemandInfo;
	}

	public void setSupplyDemandInfo(SupplyDemandInfo supplyDemandInfo) {
		this.supplyDemandInfo = supplyDemandInfo;
	}
}
