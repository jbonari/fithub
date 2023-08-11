package com.fithub.app.services;

import com.fithub.app.models.Rol;
import com.fithub.app.payload.response.JwtResponse;
import com.fithub.app.repositoris.RolRepository;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;
import com.fithub.app.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fithub.app.models.Usuario;
import org.springframework.context.MessageSource;


import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    RolRepository roleRepository;


    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Autowired
    MessageSource messageSource;

    public JwtResponse login(LoginRequest loginRequest) {
        return null;
    }

    public JwtResponse registro(AltaUsuarioRequest request, Locale locale) {
        Usuario user= Usuario.builder()
                .email(request.getEmail())
                .contrasenya(encoder.encode( request.getPassword()))
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .idioma(request.getIdioma())
                .fechaRegistro(LocalDate.now())
                .delete(false)
                .build();

        Set<String> strRoles = request.getRoles();
        Set<Rol> roles = new HashSet<>();

        for (String strRole : strRoles) {
            switch (strRole) {
                case "ROLE_ADMIN":
                    Rol adminRole = roleRepository.findByNombre("ROLE_ADMIN").orElseThrow(() -> new RuntimeException(
                            (messageSource.getMessage("msg.failure.role", null, locale)).toString()));
                    roles.add(adminRole);

                    break;
                case "ROLE_ENTRENADOR":
                    Rol entrenadorRole = roleRepository.findByNombre("ROLE_ENTRENADOR")
                            .orElseThrow(() -> new RuntimeException(
                                    (messageSource.getMessage("msg.failure.role", null, locale)).toString()));
                    roles.add(entrenadorRole);

                    break;
                case "ROLE_EMPRESA":
                    Rol empresaRole = roleRepository.findByNombre("ROLE_EMPRESA").orElseThrow(() -> new RuntimeException(
                            (messageSource.getMessage("msg.failure.role", null, locale)).toString()));
                    roles.add(empresaRole);
                    //token = SpringUtils.generarToken();
                case "ROLE_CLIENTE":
                    Rol clienteRole = roleRepository.findByNombre("ROLE_CLIENTE").orElseThrow(() -> new RuntimeException(
                            (messageSource.getMessage("msg.failure.role", null, locale)).toString()));
                    roles.add(clienteRole);
            }
        }


        //user.setRoles(roles);
        usuarioRepository.save(user);

        return JwtResponse.builder()
                //estructura de la respuesta
                .token(jwtService.getToken(user))
                .idUsuario(user.getIdUsuario())
                .build();

    }
}
