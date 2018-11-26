public abstract class CompIdentifierDecorator extends CompIdentifier{
	private CompIdentifier identifier;
	
	public CompIdentifierDecorator(CompIdentifier identifier){
		this.identifier=identifier;
	}
	
	@Override
    public void compIdentify(UrlExtractor extractor){
		 identifier.compIdentify(extractor);
	}
}
