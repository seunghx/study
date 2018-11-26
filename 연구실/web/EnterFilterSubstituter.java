import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import excel.*

/**
 * 
 * 이 클래스의 hook 메서드를 보면 extension을 검사하는 부분이 보인다. 이 보다는 웹 컴포넌트(리소스)의
 * 리소스타입을 검사하는 것이 더 좋은 방법이다.
 *
 * @author leeseunghyun
 *
 */
public class EnterFilterSubstituter extends FilterSubstituter {
	
	public EnterFilterSubstituter(ServletRequest request, String callType) {
		super(request,callType);
	}

	protected void hookForConcreteCallType() {
		int idxOfExtensionStart;
		
		if((idxOfExtensionStart=compUrl.lastIndexOf("."))!= -1) {
			String extension = compUrl.substring(idxOfExtensionStart+1);	
		 
		 	if(!extension.startsWith("html") || !extension.startsWith("jsp")) return;
		 	else changeCallTypeToIncludeCall();
		}
		return;	 	
	}
	
	private void changeCallTypeToIncludeCall() {
		ServletContext sc = request.getServletContext();
		String appName = sc.getContextPath();
		String referer = ((HttpServletRequest)request).getHeader("referer");
		
		caller = referer.substring(referer.indexOf(appName)+appName.length());
		callType = "INCLUDE_2";
	}
}
