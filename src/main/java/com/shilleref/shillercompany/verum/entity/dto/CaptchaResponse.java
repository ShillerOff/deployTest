package com.shilleref.shillercompany.verum.entity.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponse {
	private boolean success;
	@JsonAlias("error-codes")
	private Set<String> errorCode;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Set<String> getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Set<String> errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
