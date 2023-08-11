package com.fithub.app.controllers;

import com.fithub.app.payload.response.MessageResponse;
import com.fithub.app.repositoris.RolRepository;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;

import com.fithub.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Locale;

public class UsuarioController {




}
