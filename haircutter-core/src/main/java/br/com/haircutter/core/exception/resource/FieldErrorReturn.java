package br.com.haircutter.core.exception.resource;

public class FieldErrorReturn {

	private int status;
	private String error;
	private String message;
	private String field;

	public FieldErrorReturn(int status, String error, String message, String field) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.field = field;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
