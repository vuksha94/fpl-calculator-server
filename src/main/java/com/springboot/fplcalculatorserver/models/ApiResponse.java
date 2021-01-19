package com.springboot.fplcalculatorserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {

	private Status status;
	private T data;
		
	public ApiResponse<T> success(T data) {
		this.status = Status.SUCCESS;
		this.data = data;
		return this;
	}
	
	public ApiResponse<T> error(T data) {
		this.status = Status.ERROR;
		this.data = data;
		return this;
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
