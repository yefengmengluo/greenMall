package com.wk.p3.greenmall.modules.sys.listener;

import javax.servlet.ServletContext;

import com.wk.p3.greenmall.modules.sys.service.SystemService;
import org.springframework.web.context.WebApplicationContext;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
