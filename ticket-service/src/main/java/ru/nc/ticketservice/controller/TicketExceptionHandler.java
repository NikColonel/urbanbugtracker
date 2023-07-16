package ru.nc.ticketservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.nc.ticketservice.dto.Error;
import ru.nc.ticketservice.exception.TicketNotFoundException;

@ControllerAdvice
public class TicketExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TicketNotFoundException.class)
    protected ResponseEntity<Object> handleTicketNotFound(TicketNotFoundException exception, WebRequest request) {
        Error error = Error.builder()
                .description("Ticket not found")
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleCommon(RuntimeException exception, WebRequest request) {
        Error error = Error.builder()
                .description(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
