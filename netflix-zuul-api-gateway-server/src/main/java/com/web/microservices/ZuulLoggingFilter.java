package com.web.microservices;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.slf4j.internal.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{
private org.slf4j.Logger logger=LoggerFactory.getLogger(ZuulLoggingFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request ->{},request uri ->{}",request,request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		//pre/post/error
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
