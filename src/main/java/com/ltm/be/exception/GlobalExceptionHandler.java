package com.ltm.be.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "400 Response",
                                    summary = "Handle Method Argument Not Valid exception",
                                    value = """
                                            {
                                            "timestamp": "2023-10-19T06:07:35.321+00:00",
                                            "status": 400,
                                            "path": "/api/v1/...",
                                            "error": "...",
                                            "message": "..."
                                            }
                                            """
                            ))})
    })
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false).replace("uri=", ""))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(extractToGetValidationMessage(exception))
                .build();
    }

    private String extractToGetValidationMessage(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            message.append(error.getDefaultMessage());
            message.append(", ");
        }
        int len = message.length() - 2;
        if (message.charAt(len) == ',') {
            message.deleteCharAt(len);
        }
        return message.toString().strip();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRuntimeException(RuntimeException exception, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false).replace("uri=", ""))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "400 Response",
                                    summary = "Handle resource not found exception",
                                    value = """
                                            {
                                            "timestamp": "2023-10-19T06:07:35.321+00:00",
                                            "status": 404,
                                            "path": "/api/v1/...",
                                            "error": "Not Found",
                                            "message": "{data} not found"
                                            }
                                            """
                            ))})
    })
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)  {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(false).replace("uri=", ""))
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }
}

