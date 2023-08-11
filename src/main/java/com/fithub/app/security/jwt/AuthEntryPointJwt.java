package com.fithub.app.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//Controla los errores de autorizacion cuando el acceso esta denegado a alguna parte de nuestra aplicacion
public class AuthEntryPointJwt implements AuthenticationEntryPoint{

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Override
	public void commence(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException {
		logger.error("Unauthorized error: {}", authException.getMessage() + " " + request.getRequestURI());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");	
	}


}
