package com.ymt.mirage.user.support.duiba;

public class CreditConsumeResult {

	private boolean success;
	private String status;
	private String errorMessage="";
	private String bizId="";
	private Long credits=-1L;//用户积分余额
	
	public CreditConsumeResult(boolean success){
		this.success=success;
		if(success){
			this.status = "ok";
		}else{
			this.status = "fail";
		}
	}
	
	
	public String toString(){
		if(success){
			return "{'status':'ok','errorMessage':'','bizId':'"+bizId+"','credits':'"+credits+"'}";
		}else{
			return "{'status':'fail','errorMessage':'"+errorMessage+"','credits':'"+credits+"'}";
		}
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public Long getCredits() {
		return credits;
	}


	public void setCredits(Long credits) {
		this.credits = credits;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
