package com.openapi.nasa.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class NasaGlobalExceptionHandler {

    // handling exceptions for NasaApodEntity //
    @ExceptionHandler
    public ResponseEntity<NasaErrorResponse> handle(NasaNotFoundException exec)
    {
        NasaErrorResponse response=new NasaErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimeStamp(System.currentTimeMillis());
        response.setMessage(exec.getMessage());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    // globally handling other exceptions for NasaApodEntity //
    @ExceptionHandler
    public ResponseEntity<NasaErrorResponse> handle(Exception exec)
    {
        NasaErrorResponse response=new NasaErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());
        response.setMessage(exec.getMessage());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    // Handling Exceptions For 403 Access Denied Which Redirects to Access-Denied Page //
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException exec, HttpServletRequest request) {
        // You can customize the behavior here, for example, redirect to a specific access-denied page.
        return "nasa/access-denied";
    }

    // Handling No StaticResourceFoundException which occurs during session managements- Avoids Application Crash //
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleAccessDeniedException(NoResourceFoundException exec, HttpServletRequest request) {
        // You can customize the behavior here, for example, redirect to a specific access-denied page.
        return "nasa/home-page";
    }

    //handling exception and ignoring for httpmesageconverterexception //
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Void> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type as needed

        return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
