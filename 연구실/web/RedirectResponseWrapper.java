package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RedirectResponseWrapper extends HttpServletResponseWrapper{
	private HttpServletRequest request;
	
	public RedirectResponseWrapper(ServletRequest request, ServletResponse response){
		super((HttpServletResponse)response);
		this.request = (HttpServletRequest)request;
	}
	
	public void sendRedirect(String destUrl) throws IOException{
				
		FilterSubstituter substituter = new FilterSubstituter(request,"REDIRECT");
		
		class reDirectIdentifier implements CompIdentifier{
			private String destUrl;
			
			public reDirectIdentifier(String destUrl){
				this.destUrl= destUrl;
			}
			
			public String identify(ServletRequest req){
				if(destUrl.lastIndexOf('/') == -1) return "/"+destUrl;
				else return destUrl.substring(destUrl.lastIndexOf('/'));
			}
			
		}
		
		substituter.doPreFilter(new reDirectIdentifier(destUrl));
		substituter.doPostFilter();
		
		super.sendRedirect(destUrl);
	}
	
	

}
