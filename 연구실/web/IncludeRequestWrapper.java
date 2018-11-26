package web;

import javax.servlet.*;
import javax.servlet.http.*;

public class IncludeRequestWrapper extends HttpServletRequestWrapper{
	private String includedCompUrl;
	
	
	public IncludeRequestWrapper(ServletRequest request){
		super((HttpServletRequest)request);
		
	}
	
	public RequestDispatcher getRequestDispatcher(String url){
		if(url.lastIndexOf('/')== -1)
			includedCompUrl = "/"+url;
		else includedCompUrl = url.substring(url.lastIndexOf('/'));
		
		return super.getRequestDispatcher(url);
	}
	
	public String getCompUrl() {
		return includedCompUrl;
	}
}
