package org.gn.udip.service.search.vo;

public class ParserMasterVO {
	
	private String name;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParserMasterVO(Long id, String parserName) {
		this.setId(id);
		this.setName(parserName);
	}

}
