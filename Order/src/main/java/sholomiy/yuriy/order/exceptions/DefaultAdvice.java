package sholomiy.yuriy.order.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<String> handleException(OrderServiceException e) {
         return new ResponseEntity<String>(e.getMessage(), e.getHttpStatus());
    }

}