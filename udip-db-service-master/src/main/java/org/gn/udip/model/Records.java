package org.gn.udip.model;

public class Records {

	private FetcherKafkaDetail value;
	
	public Records(){}
	
	public Records(FetcherKafkaDetail value)
	{
		this.setValue(value);
	}

	public FetcherKafkaDetail getValue() {
		return value;
	}

	public void setValue(FetcherKafkaDetail value) {
		this.value = value;
	}
	
}
