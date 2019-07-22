package org.gn.udip.service.search.vo;

import java.util.ArrayList;
import java.util.List;

import org.gn.udip.model.ParserMaster;

public class ParserSearchResult {

	private int count;

	private List<ParserMasterVO> parsers = new ArrayList<>();;

	public ParserSearchResult(int count, List<ParserMaster> masterList) {
		super();
		this.setCount(count);
		// parse the master list and add a new VO object to the list
		masterList.forEach(parser -> {
			parsers.add(new ParserMasterVO(parser.getParserId(), parser.getParserName()));
		});
		this.setParsers(parsers);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ParserMasterVO> getParsers() {
		return parsers;
	}

	public void setParsers(List<ParserMasterVO> parsers) {
		this.parsers = parsers;
	}

}
