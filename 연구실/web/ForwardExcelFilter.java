package web;

import java.io.IOException;
import javax.servlet.*;



public class ForwardExcelFilter implements Filter {
    
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   FilterSubstituter substituter = new FilterSubstituter(request,"FORWARD");
	   substituter.doPreFilter(new EnterForwardIdentifier());
		 
	   chain.doFilter(request,response);
		
	   substituter.doPostFilter();
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
