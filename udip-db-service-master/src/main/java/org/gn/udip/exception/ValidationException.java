package org.gn.udip.exception;

public class ValidationException extends Exception{
	
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

	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String errCode,String errDesc) {
		super(errCode + " : "+ errDesc);
		this.errCode  = errCode;
		this.errDesc = errDesc ;
		
	}

}
