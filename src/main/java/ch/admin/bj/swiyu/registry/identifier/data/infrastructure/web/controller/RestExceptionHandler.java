/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.infrastructure.web.controller;

import ch.admin.bj.swiyu.registry.identifier.data.api.ApiErrorDto;
import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotFoundException;
import ch.admin.bj.swiyu.registry.identifier.data.common.exception.ResourceNotReadyException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoundException(
        final ResourceNotFoundException exception,
        final HttpServletRequest request
    ) {
        var apiError = new ApiErrorDto(HttpStatus.NOT_FOUND, exception.getMessage());
        log.trace("Resource not found for URL {}", request.getRequestURI());
        return new ResponseEntity<>(apiError, apiError.status());
    }

    @ExceptionHandler(ResourceNotReadyException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotReadyException(
        final ResourceNotReadyException exception,
        final HttpServletRequest request
    ) {
        var error = new ApiErrorDto(HttpStatus.TOO_EARLY, exception.getMessage());
        log.trace("Resource not ready for URL {}", request.getRequestURI());
        return new ResponseEntity<>(error, error.status());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleBrokenPipeException(IOException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("Broken pipe")) {
            // This is most likely a wrapped client abort exception meaning the client has already disconnected
            // Because there's no point in returning a response null is returned
            log.debug("Client aborted connection", ex);
            return null;
        }
        log.error("Unhandled IO exception occurred", ex);
        var error = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, error.status());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleUnexpectedStreamClosing(MultipartException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("Stream ended unexpectedly")) {
            log.debug("Stream ended unexpectedly", ex);
            var error = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(error, error.status());
        }
        log.error("Unhandled MultipartException exception occurred", ex);
        var error = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), error.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handle(final Exception exception, final HttpServletRequest request) {
        final ApiErrorDto apiError = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("Detected unhandled exception for url {}", request.getRequestURL(), exception);
        return new ResponseEntity<>(apiError, apiError.status());
    }
}
