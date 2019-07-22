package org.gn.udip.model;

public class ParserDetailResponsePojo {
	
	private Long parserId;
	private String parserName;
	private String scriptName;
	private String parserCommand;
	
	public Long getParserId() {
		return parserId;
	}
	public void setParserId(Long parserId) {
		this.parserId = parserId;
	}
	public String getParserName() {
		return parserName;
	}
	public void setParserName(String parserName) {
		this.parserName = parserName;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public String getParserCommand() {
		return parserCommand;
	}
	public void setParserCommand(String parserCommand) {
		this.parserCommand = parserCommand;
	}

}
