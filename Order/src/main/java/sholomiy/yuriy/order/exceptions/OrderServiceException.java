package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public abstract class OrderServiceException extends RuntimeException {

	private static final long serialVersionUID = 5438472322733759646L;

	public OrderServiceException(String message) {
		super(message);
	}
	
	public OrderServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public abstract HttpStatus getHttpStatus();
	
}
