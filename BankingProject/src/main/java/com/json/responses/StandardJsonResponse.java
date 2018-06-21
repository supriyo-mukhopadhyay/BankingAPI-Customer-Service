package com.json.responses;

import java.util.HashMap;

public class StandardJsonResponse {

	static String DEFAULT_MSG_NAME_FIELD = "message";
	static String DEFAULT_MSG_TITLE_FIELD = "title";
	static String DEFAULT_MSG_TITLE_VALUE = "Internal Server Error";
	static String DEFAULT_MSG_NAME_VALUE = "The server encountered an unexpected condition which prevented it from fulfilling the request.";
	static String RESOURCE_NOT_FOUND_MSG = "The resource requested is not found. Please check your resource ID.";

	
	private boolean success = false;

	private HashMap<String, String> messages;

	private HashMap<String, String> errors;

	private HashMap<String, Object> data;

	private int httpResponseCode;

	public StandardJsonResponse() {

		messages = new HashMap<String, String>();
		errors = new HashMap<String, String>();
		data = new HashMap<String, Object>();
	}

	/**
	 * @param success
	 *            the success to set if success is false a default message and title is added
	 */

	public void setSuccess(boolean success) {
		this.success = success;
		if (!success) {
			messages.put(DEFAULT_MSG_NAME_FIELD, DEFAULT_MSG_NAME_VALUE);
			messages.put(DEFAULT_MSG_TITLE_FIELD, DEFAULT_MSG_TITLE_VALUE);
		}
	}

	/**
	 * @return the success
	 */
	
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */

	public void setMessages(HashMap<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the messages
	 */

	public HashMap<String, String> getMessages() {
		return messages;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	/**
	 * @return the errors
	 */
	
	public HashMap<String, String> getErrors() {
		return errors;
	}

	
	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	/**
	 * @param success
	 * @param title
	 *            - message title
	 * @param message
	 *            -message body
	 */
	public void setSuccess(boolean success, String title, String message) {
		this.success = success;
		messages.put(DEFAULT_MSG_NAME_FIELD, (message == null || message.isEmpty()) ? "" : message);
		messages.put(DEFAULT_MSG_TITLE_FIELD, (title == null || title.isEmpty()) ? "" : title);
	}

	
	public void setHttpResponseCode(int code) {
		httpResponseCode = code;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

}
