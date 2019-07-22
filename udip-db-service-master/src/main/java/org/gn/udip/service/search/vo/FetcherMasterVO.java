package org.gn.udip.service.search.vo;


/**
 * @author akshay
 *
 */
public class FetcherMasterVO {

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

	public FetcherMasterVO(Long id, String fetcherName) {
		this.setId(id);
		this.setName(fetcherName);
	}


}
