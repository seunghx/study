import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import excel.*;

public class FilterSubstituter {
	protected ServletRequest request;
	
	protected String caller;
	protected String callType;
	protected String compUrl;
	
	public FilterSubstituter(ServletRequest request,String callType){
		this.request = request;
		this.callType = callType;
	}
	
	public void doPreFilter(CompIdentifier identifier){
        ServletContext sc = request.getServletContext();
		String appName = sc.getContextPath();
		String fileName = sc.getRealPath("/WEB-INF/excelfolder/") + "\\\\" + appName.substring(1);
		
		compUrl = identifier.identify(request);
		caller = (String)request.getAttribute("CURRENT_COMP");
		
		request.setAttribute("CURRENT_COMP", compUrl);
		
		hookForConcreteCallType();
		
		ExcelDataType dataType1 = new WebCollabMainNodeSet(compUrl);
		ExcelDataType dataType2 = new WebCompCallEdgeList(caller,compUrl,callType);
		
		ExcelHandler handler1 = new ExcelHandler(fileName,dataType1);
		ExcelHandler handler2 = new ExcelHandler(fileName,dataType2);
		
		handler1.doHandle();
		handler2.doHandle();
	}
	
	public void doPostFilter(){
		request.setAttribute("CURRENT_COMP", caller);
	}
	
	protected void hookForConcreteCallType(){}
	
}
