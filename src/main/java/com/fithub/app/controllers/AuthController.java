package com.fithub.app.controllers;

import com.fithub.app.models.Rol;
import com.fithub.app.payload.response.JwtResponse;
import com.fithub.app.payload.response.MessageResponse;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;
import com.fithub.app.request.LoginRequest;
import com.fithub.app.services.AuthService;
import com.fithub.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final MessageSource messageSource;
	private final UsuarioRepository usuarioRepository;

	@PostMapping(value="login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping(value = "register")
	public ResponseEntity<?> register(@Valid @RequestBody AltaUsuarioRequest request,Locale locale, BindingResult result){

		ResponseEntity<?> resultado;
		boolean resreg = false;

		if (result.hasErrors()) {
			resultado = ResponseEntity.badRequest().body(
					new MessageResponse((messageSource.getMessage("msg.failure.register", null, locale)).toString()));
		} else {
			if (usuarioRepository.existsByEmail(request.getEmail())) {
				return ResponseEntity.badRequest().body(
						new MessageResponse((messageSource.getMessage("msg.failure.email", null, locale)).toString()));
			}

			resreg = authService.registro(request, locale);

			if (resreg) {
				resultado = ResponseEntity.ok(new MessageResponse(
						(messageSource.getMessage("msg.success.register", null, locale)).toString()));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(
						(messageSource.getMessage("msg.failure.register", null, locale)).toString()));
			}
		}

		return resultado;
	}

	/*
	@PostMapping(value = "register")
	public ResponseEntity<?> register(@Valid @RequestBody AltaUsuarioRequest request,Locale locale,BindingResult result){

		ResponseEntity<?> resultado;
		boolean resreq=false;

		if (result.hasErrors()){
			resultado= ResponseEntity.badRequest().body(
					new MessageResponse((messageSource.getMessage("msg.failure.register",null,locale)).toString()));
		}else{
			if (usuarioRepository.existsByEmail(request.getEmail())){
				return ResponseEntity.badRequest().body(
						new MessageResponse((messageSource.getMessage("msg.failure.email", null, locale)).toString()));
			}
		}


		return ResponseEntity.ok(authService.registro(request,locale));
	}*/

}
