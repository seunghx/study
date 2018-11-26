public class BasicCompIdentifier extends CompIdentifier{
	private ServletRequest request;
	
	public BasicCompIdentifier(String callType,ServletRequest request){
			this.request=request;
			this.callType=callType;
	}
	
	@Override
    public void compIdentify(UrlExtractor extractor){
		compUrl = extractor.extract(request);
		caller = (String)request.getAttribute("CURRENT_COMP");
	}
}
