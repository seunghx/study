package web;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class EnterExcelFilter implements Filter {
	
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
															throws IOException, ServletException {
		
		request.setAttribute("CURRENT_COMP", "/WebContainer");
		FilterSubstituter substituter = new FilterSubstituter(request, "ENTER");
		substituter.doPreFilter(new EnterForwardIdentifier());
		
		chain.doFilter(new IncludeRequestWrapper(request), 
					new RedirectResponseWrapper(request,response));
		
		substituter.doPostFilter();
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}	
}
