/**
 * 
 * Http Referrer를 이용한 컴포넌트 추출방법의 한계 때문에 해당 클래스와 같이
 * 특정 웹앱에 의존적인 클래스를 만들 수 밖에 없었다.이 클래스의 urlFilteringForDreamApp() 메서드는
 * url을 검사하여 filtering 작업을 수행하는 데이 구현은 좋지 못한 구현이다. contentType을 비교하는 방법이 더 적절하다.
 * (이 메서드 구현 당시에 사용한 test 웹앱이 contentType을 null로 가져서 이런 방법을 사용하게 되었다.
 *
 * @author leeseunghyun
 */
		

public class DreamAppCompIdentifier extends BasicCompIdentifier{
	private CompIdentifier identifier;
	
	public DreamAppCompIdentifier(String callType,ServletRequest request,CompIdentifier identifier){
		super(callType,request);
		this.identifier= identifier;
    }
    
	@Override
    public void compIdentify(UrlExtractor extractor){
		super.compIdentify(extractor);
		
		// Proxy 
		if(!urlFilteringForDreamApp()) return;
			identifier.compIdentify(extractor);
	}
	
	protected boolean urlFilteringForDreamApp(){
		if(("flash.html".equals(compUrl))){
			caller = "/index.html";
			callType = "2_INCLUDE";
			return false;
		}
		
		if(compUrl.startsWith("/filmdesc"))	{ //caller는 엑셀파일로부터 명시적으로 교체 해줘야함.
			compUrl= "/filmdesc.jsp";
			callType = "2_INCLUDE";
			return false;
		}
		if(compUrl.startsWith("/cover/")){
			caller = "/filmdesc.jsp";
			callType = "2_INCLUDE";
			return false;
		}
		return true;
	}
}
