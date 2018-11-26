package web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class EnterForwardIdentifier implements CompIdentifier {
	
	public String identify(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String compUrl =httpRequest.getServletPath()+(httpRequest.getQueryString()!=null? "?" + httpRequest.getQueryString() : "");
		return compUrl;
	}

}
