package org.gn.udip.exception;

public class MailerException extends Exception{
	
	public MailerException(Exception e){
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

	public MailerException(String message) {
		super(message);
	}
	public MailerException(String errCode,String errDesc) {
		this(errCode + " : "+ errDesc);
		this.setErrCode(errCode);
		this.setErrDesc(errDesc);
	}

}
