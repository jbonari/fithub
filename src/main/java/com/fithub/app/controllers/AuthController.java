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

	//@Autowired
	//UsuarioRepository usuarioRepository;

	//@Autowired
	//JwtUtils jwtUtils;

	//@Autowired
	//MessageSource messageSource;

	/*

	@PermitAll
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result,
											  @PathVariable("locale") Locale locale) {

		ResponseEntity<?> resultado;

		if (result.hasErrors()) {
			resultado = ResponseEntity.badRequest().body(
					new MessageResponse((messageSource.getMessage("msg.failure.login", null, locale)).toString()));
		} else {

			if (!usuarioRepository.existsByEmail(loginRequest.getEmail())
					&& !usuarioRepository.existsByContrasenya(loginRequest.getPassword())) {
				resultado = ResponseEntity.badRequest().body(new MessageResponse(
						(messageSource.getMessage("msg.failure.credentials", null, locale)).toString()));
			} else {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtUtils.generateJwtToken(authentication);

				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
				List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
						.collect(Collectors.toList());

				resultado = ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getId(), userDetails.getIdioma() ,roles));
			}
		}

		return resultado;
	}*/

	@PostMapping(value="login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping(value = "register")
	public ResponseEntity<JwtResponse> register(@RequestBody AltaUsuarioRequest request,Locale locale){

		return ResponseEntity.ok(authService.registro(request,locale));
	}

}
