package com.fithub.app.controllers;

import com.fithub.app.models.Rol;
import com.fithub.app.payload.response.JwtResponse;
import com.fithub.app.payload.response.MessageResponse;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;
import com.fithub.app.request.LoginRequest;
import com.fithub.app.services.AuthService;
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

	@PostMapping(value="login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping(value = "register")
	public ResponseEntity<JwtResponse> register(@RequestBody AltaUsuarioRequest request,Locale locale){

		return ResponseEntity.ok(authService.registro(request,locale));
	}

}
