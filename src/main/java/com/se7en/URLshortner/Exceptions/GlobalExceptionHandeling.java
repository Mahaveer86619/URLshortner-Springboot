package com.se7en.URLshortner.Exceptions;

import com.se7en.URLshortner.Payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandeling {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFound ex) {

        String message = ex.getMessage();
        ApiResponse api_response = new ApiResponse(message, false);

        return new ResponseEntity<ApiResponse>(api_response, HttpStatus.NOT_FOUND);
    }


}
