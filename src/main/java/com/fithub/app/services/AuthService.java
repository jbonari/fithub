package com.fithub.app.services;

import com.fithub.app.models.Rol;
import com.fithub.app.payload.response.JwtResponse;
import com.fithub.app.payload.response.MessageResponse;
import com.fithub.app.repositoris.RolRepository;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;
import com.fithub.app.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fithub.app.models.Usuario;
import org.springframework.context.MessageSource;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RolRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final MessageSource messageSource;
    private final AuthenticationManager authenticationManager;

    public JwtResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        //genearamos el token
        UserDetails userDetails = new Usuario(usuario.getEmail(), usuario.getContrasenya(), usuario.getIdioma());

        String token=jwtService.getToken(userDetails);


        List<String> authorities = usuario.getRoles().stream()
                .map(rol -> rol.getNombre())
                .collect(Collectors.toList());

        //respuesta Jwt
        return JwtResponse.builder()
                .token(jwtService.getToken(userDetails))
                .email(usuario.getUsername())
                .idUsuario(usuario.getIdUsuario())
                .idioma(usuario.getIdioma())
                .roles(authorities)
                .type("Bearer")
                .build();
    }

    public boolean registro(AltaUsuarioRequest request, Locale locale) {

        boolean resultado=false;

        Usuario user = Usuario.builder()
                .email(request.getEmail())
                .contrasenya(encoder.encode(request.getPassword()))
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

        try {
            usuarioRepository.save(user);
            // Si llegamos a este punto sin errores, cambiamos el valor de resultado a true
            resultado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }






        // Respuesta de éxito
        /*

        List<String> authorities = user.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toList());



        JwtResponse jwtResponse = JwtResponse.builder()
                //estructura de la respuesta
                .token(jwtService.getToken(user))
                .email(user.getEmail())
                .idUsuario(user.getIdUsuario())
                .idioma(user.getIdioma())
                .roles(authorities)
                .type("Bearer")
                .build();

        return ResponseEntity.ok(jwtResponse);*/

        return resultado;

    }
}
