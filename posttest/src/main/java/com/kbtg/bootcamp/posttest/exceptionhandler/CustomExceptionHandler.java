package com.kbtg.bootcamp.posttest.exceptionhandler;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.GetAllLotteryException;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryDeleteException;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryIdNotFound;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryPurchaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse("Invalid request body.");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomExceptionResponse response = new CustomExceptionResponse("UserId must contain only number 10 digits");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LotteryIdNotFound.class})
    public ResponseEntity<Object> handlerExceptionIdNotFound(LotteryIdNotFound e){
        CustomExceptionResponse response = new CustomExceptionResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {LotteryPurchaseException.class})
    public ResponseEntity<Object> handlerExceptionLotteryPurchase(LotteryPurchaseException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {LotteryDeleteException.class})
    public ResponseEntity<Object> handlerDeleteTicketFromUser(LotteryDeleteException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {GetAllLotteryException.class})
    public ResponseEntity<Object> handlerRequestBodyInvalid(GetAllLotteryException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
