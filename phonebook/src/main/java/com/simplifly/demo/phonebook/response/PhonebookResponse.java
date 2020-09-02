
package com.simplifly.demo.phonebook.response;

public class PhonebookResponse {
	private String status;
	private String errorMessage;
	private Object responseData;

	public PhonebookResponse(String status, String errorMessage, Object responseData) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.responseData = responseData;
	}

	public static PhonebookResponse constructSuccessResponse(Object responseData) {
		return new PhonebookResponse("SUCCESS", "", responseData);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

}