package web;

import java.io.IOException;
import javax.servlet.*;


public class IncludeExcelFilter implements Filter {
    
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterSubstituter substituter = new FilterSubstituter(request,"INCLUDE");
		
		substituter.doPreFilter(new CompIdentifier(){
		    public String identify(ServletRequest request){
		    	IncludeRequestWrapper reqWrapper = (IncludeRequestWrapper)request;
		    	return reqWrapper.getCompUrl();
		    }
		});
		chain.doFilter(request, response);
		substituter.doPostFilter();
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
