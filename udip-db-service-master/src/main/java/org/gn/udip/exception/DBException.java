package org.gn.udip.exception;

public class DBException extends Exception {
	
	public DBException(Exception e){
		super(e);
	}
	
	private String errCode ;
	private String errDesc ;
	
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public DBException(String message) {
		super(message);
	}
	public DBException(String errCode,String errDesc) {
		this(errCode + " : "+ errDesc);
		this.setErrCode(errCode);
		this.setErrDesc(errDesc);
	}
}
