package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class InventoryNotFoundException extends OrderServiceException {

	private static final long serialVersionUID = -5279775907186761809L;

	private static final String EXCEPTION_MESSAGE_FORMAT = "Could not found inventory product IDs: %s";
	
	public InventoryNotFoundException(String ids) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, ids));
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
