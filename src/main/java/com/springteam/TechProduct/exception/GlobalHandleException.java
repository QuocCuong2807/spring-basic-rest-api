package com.springteam.TechProduct.exception;

import com.springteam.TechProduct.dto.ErrorResponeDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

//One of interceptor class responsible for global handle exception
@ControllerAdvice
public class GlobalHandleException {

    //create ErrorResponse's variable
    private ErrorResponeDTO error;

    //handle product not found exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponeDTO> handleProductNotFound(ProductNotFoundException exc) {

        //create ErrorResponse's instance
        error = new ErrorResponeDTO();;

        //set attribute
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error .setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //handle Role not found exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponeDTO> handleRoleNotFoundException(RoleNotFoundException exc){
        error = new ErrorResponeDTO();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    //handle user not found exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponeDTO> handleUserNotFoundException(UserNotFoundException exc){
        error = new ErrorResponeDTO();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    //handle file not found exception
    //@ExceptionHandler({IOException.class})
    @ExceptionHandler
    public ResponseEntity<ErrorResponeDTO> handleFileNotFoundException(FileNotFoundException exc){
        error = new ErrorResponeDTO();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    //handle casting string to int exception
    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<ErrorResponeDTO> handleTypeCastingException(NumberFormatException exc){
        error = new ErrorResponeDTO();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("invalid input");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
