package com.springboot.fplcalculatorserver.models;

public class ApiResponse <T> {

	private Status status;
	private T data;
	
	public ApiResponse() {}
	
	public ApiResponse(Status status, T data) {
		this.status = status;
		this.data = data;
	}
	
	public ApiResponse success(T data) {
		this.status = Status.SUCCESS;
		this.data = data;
		return this;
	}
	
	public ApiResponse error(T data) {
		this.status = Status.ERROR;
		this.data = data;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", data=" + data + "]";
	}
	
	
}

enum Status { 
	
	SUCCESS("success"), ERROR("error");

	private String value;
	
	Status(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
