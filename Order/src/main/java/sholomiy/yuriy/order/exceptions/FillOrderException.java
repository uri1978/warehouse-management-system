package sholomiy.yuriy.order.exceptions;

import org.springframework.http.HttpStatus;

public class FillOrderException extends OrderServiceException {

	private static final long serialVersionUID = -5279775907186761809L;

	public FillOrderException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
