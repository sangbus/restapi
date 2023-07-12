//package com.example.demo;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@RestController
//@ControllerAdvice
//public class EntityExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
//        ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
//                .timestamp(new Date())
//                .message(ex.getMessage())
//                .details(request.getDescription(false))
//                .build();
//        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
//
//        ExceptionResponse exceptionResponse = new ExceptionResponse().builder()
//                .timestamp(new Date())
//                .message(ex.getMessage())
//                .details(request.getDescription(false))
//                .build();
//        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
//
//    }
//}
