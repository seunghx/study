public abstract class CompIdentifier {
	protected String compUrl;
	protected String caller;
	protected String callType;
	
	public String getCompUrl(){
		return compUrl;
		
	}
	public String getCaller(){
		return caller;
	}
	public String getCallType(){
		return callType;
	}
	
	public abstract void compIdentify(UrlExtractor extractor);
}
