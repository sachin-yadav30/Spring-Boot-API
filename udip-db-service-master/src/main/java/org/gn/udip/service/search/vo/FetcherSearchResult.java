package org.gn.udip.service.search.vo;

import java.util.ArrayList;
import java.util.List;

import org.gn.udip.model.FetcherMaster;

public class FetcherSearchResult {

	public FetcherSearchResult(int count, List<FetcherMaster> masterList) {
		super();

		this.setCount(count);
		// parse the master list and add a new VO object to the list
		masterList.forEach(bean -> {
			fetchers.add(new FetcherMasterVO(bean.getFetcherId(), bean.getFetcherName()));
		});
		this.setFetchers(fetchers);
	}

	private int count;

	private List<FetcherMasterVO> fetchers = new ArrayList<>();;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<FetcherMasterVO> getFetchers() {
		return fetchers;
	}

	public void setFetchers(List<FetcherMasterVO> fetchers) {
		this.fetchers = fetchers;
	}

}
