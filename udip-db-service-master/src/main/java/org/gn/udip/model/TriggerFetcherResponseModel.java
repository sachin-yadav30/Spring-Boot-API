package org.gn.udip.model;

import java.util.List;

public class TriggerFetcherResponseModel {

	String value_schema;
	List<Records> records;
	
	public String getValue_schema() {
		return value_schema;
	}
	public void setValue_schema(String value_schema) {
		this.value_schema = value_schema;
	}
	
	public List<Records> getRecords() {
		return records;
	}
	public void setRecords(List<Records> records) {
		this.records = records;
	}
		
}
