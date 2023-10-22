package com.example.springbootapp.conf;


import com.example.springbootapp.domain.dto.ErrorResponseDTO;
import com.example.springbootapp.exception.GenericException;
import com.example.springbootapp.exception.PriceNotFoundException;
import com.example.springbootapp.exception.ProductBrandMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler({GenericException.class})
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDTO.builder().message("There was an unknown error").build());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDTO.builder().message("Entity not found").build());
    }

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handlePriceNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler({ProductBrandMismatchException.class})
    public ResponseEntity<ErrorResponseDTO> handleProductBrandMismatchException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponseDTO> handleMissingServletRequestParameterException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponseDTO.builder().message("A required parameter is missing").build());
    }


}
