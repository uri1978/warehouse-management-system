package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class ProductsNotFoundException extends OrderServiceException {

	private static final long serialVersionUID = 5894700737250159272L;
	
	private static final String EXCEPTION_MESSAGE_FORMAT = "Could not found product IDs: %s";
	
	public ProductsNotFoundException(String ids) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, ids));
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
