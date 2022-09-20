package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class CatalogueNotFoundException extends OrderServiceException {
	
	private static final long serialVersionUID = -6510058837009708100L;
	
	private static final String EXCEPTION_MESSAGE_FORMAT = "Could not found catalogue with product IDs: %s";

	public CatalogueNotFoundException(String ids) {
		super(String.format(EXCEPTION_MESSAGE_FORMAT, ids));
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
