package org.gn.udip.service.search.vo;

public class SearchResult {
	
	private String text;
	
	private ServiceSearchResult serviceSearchResult;
	private FetcherSearchResult fetcherSearchResult;
	private ParserSearchResult parserSearchResult;

	public ParserSearchResult getParserSearchResult() {
		return parserSearchResult;
	}

	public void setParserSearchResult(ParserSearchResult parserSearchResult) {
		this.parserSearchResult = parserSearchResult;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ServiceSearchResult getServiceSearchResult() {
		return serviceSearchResult;
	}

	public void setServiceSearchResult(ServiceSearchResult serviceSearchResult) {
		this.serviceSearchResult = serviceSearchResult;
	}

	public FetcherSearchResult getFetcherSearchResult() {
		return fetcherSearchResult;
	}

	public void setFetcherSearchResult(FetcherSearchResult fetcherSearchResult) {
		this.fetcherSearchResult = fetcherSearchResult;
	}
	
	

}
