package com.fithub.app.services;



import com.fithub.app.models.Rol;
import com.fithub.app.models.Usuario;
import com.fithub.app.repositoris.RolRepository;
import com.fithub.app.repositoris.UsuarioRepository;
import com.fithub.app.request.AltaUsuarioRequest;
import com.fithub.app.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioService  {
	/*
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RolRepository roleRepository;
	@Autowired
	MessageSource messageSource;




	@Transactional
	public boolean registrarUsuario(AltaUsuarioRequest altaUsuarioRequest, Locale locale) {

		boolean resultado = false;
		String token =null;

		Usuario user = new Usuario(altaUsuarioRequest.getEmail(), encoder.encode(altaUsuarioRequest.getPassword()),
				altaUsuarioRequest.getIdioma());

		Set<String> strRoles = altaUsuarioRequest.getRole();
		Set<Rol> roles = new HashSet<>();

		Iterator<String> iterator = strRoles.iterator();

		while(iterator.hasNext()) {
			switch (iterator.next()) {
				case "ROLE_ADMIN":
					Rol adminRole = roleRepository.findByNom("ROLE_ADMIN").orElseThrow(() -> new RuntimeException(
							(messageSource.getMessage("msg.failure.role", null, locale)).toString()));
					roles.add(adminRole);

					break;
				case "ROLE_ENTRENADOR":
					Rol entrenadorRole = roleRepository.findByNom("ROLE_ENTRENADOR")
							.orElseThrow(() -> new RuntimeException(
									(messageSource.getMessage("msg.failure.role", null, locale)).toString()));
					roles.add(entrenadorRole);

					break;
				case "ROLE_EMPRESA":
					Rol empresaRole = roleRepository.findByNom("ROLE_EMPRESA").orElseThrow(() -> new RuntimeException(
							(messageSource.getMessage("msg.failure.role", null, locale)).toString()));
					roles.add(empresaRole);
					token = SpringUtils.generarToken();
				case "ROLE_CLIENTE":
					Rol clienteRole = roleRepository.findByNom("ROLE_CLIENTE").orElseThrow(() -> new RuntimeException(
							(messageSource.getMessage("msg.failure.role", null, locale)).toString()));
					roles.add(clienteRole);
			}
		};

		user.setRoles(roles);
		//userRepository.save(user);
		resultado=true;

		//Empresa empresa = null;
//
		//if (altaUsuarioRequest.getEmpresa() != null) {
		//	empresa = empresaRepository.save(altaUsuarioRequest.getEmpresa());
		//	user.setRoles(roles);
		//	empresa.setToken(token);
		//	user.setEmpresa(empresa);
		//	userRepository.save(user);
		//}
//
		//if (empresa != null)
		//	resultado = true;

		return resultado;
	}*/
}
