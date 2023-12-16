package com.springteam.TechProduct.securityconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springteam.TechProduct.dto.ErrorResponeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.net.http.HttpHeaders;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private HandlerExceptionResolver resolver;

    @Autowired
    public JwtAuthEntryPoint (@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver){
        this.resolver = resolver;
    }


    //handle authentication exception (could not handle in controller advice because filter responsible for handle)
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ErrorResponeDTO err = new ErrorResponeDTO();
        err.setMessage("JWT was expired or invalid");
        err.setTimeStamp(System.currentTimeMillis());
        err.setStatus(HttpStatus.FORBIDDEN.value());
        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(), err);

    }
}
