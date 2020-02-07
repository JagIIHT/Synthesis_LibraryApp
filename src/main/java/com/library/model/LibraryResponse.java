package com.library.model;

public class LibraryResponse<T> {

	private String status;
	private T responseBody;

	public LibraryResponse(T responseBody, String status) {
		super();
		this.status = status;
		this.responseBody = responseBody;
	}

	public LibraryResponse() {
	}

	public String getStatus() {
		return status;
	}

	public T getResponseBody() {
		return responseBody;
	}

}
