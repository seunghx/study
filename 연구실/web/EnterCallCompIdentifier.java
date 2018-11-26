public class EnterCallCompIdentifier extends CompIdentifierDecorator {
	private ServletRequest request;
	
	public EnterCallCompIdentifier(CompIdentifier identifier, ServletRequest request){
		super(identifier);
		this.request= request;
	}
	
	@Override
    public void compIdentify(UrlExtractor extractor){
		super.compIdentify(extractor);
		additionalCompIdentify();		
	}
	
	private void additionalCompIdentify(){				
		int lastIdx=(compUrl.indexOf("?")!= -1)? compUrl.indexOf("?"):compUrl.length();
		String urlWithoutQuery= compUrl.substring(0,lastIdx);
		
		int idxOfExtensionStart = urlWithoutQuery.lastIndexOf(".");
		if(idxOfExtensionStart == -1) return;
		
		
		String extension= urlWithoutQuery.substring(idxOfExtensionStart+1);		 
	 	if(extension.startsWith("html") || extension.startsWith("jsp")) return;
	 	else changeCollaboration();
	}
	
	private void changeCollaboration(){
		String appName = request.getServletContext().getContextPath();
		String referer = ((HttpServletRequest)request).getHeader("referer");
		
		caller = referer.substring(referer.indexOf(appName)+appName.length());
		callType = "2_INCLUDE";
	}
}

