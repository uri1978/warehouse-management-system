package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends OrderServiceException {
	
	private static final long serialVersionUID = -6510058837009708100L;
	
	private static final String EXCEPTION_MESSAGE_FORMAT = "Order wasn't found by ID: %s";

	public OrderNotFoundException(String ids) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, ids));
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
