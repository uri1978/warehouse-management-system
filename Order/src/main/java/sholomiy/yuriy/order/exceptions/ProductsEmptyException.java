package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class ProductsEmptyException extends OrderServiceException {

	private static final long serialVersionUID = -5279775907186761809L;

	private static final String EXCEPTION_MESSAGE_FORMAT = "Products empty";
	
	public ProductsEmptyException() {
		super(EXCEPTION_MESSAGE_FORMAT);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
