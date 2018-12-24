package com.crud.example.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFound extends RuntimeException {
	private Date timeStamp;
	private String message;
	private String details;

	public UserNotFound(String message) {
		super();
		this.message = message;
	}

	public UserNotFound(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
