package com.baosight.sggk.du.hm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.log4j.Logger;

public class ServiceDUHM12 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHM12.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		return outInfo;
	}
}
