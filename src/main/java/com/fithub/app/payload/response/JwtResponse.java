package com.fithub.app.payload.response;

import com.fithub.app.models.Rol;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class JwtResponse {
	private String email;
	private Long idUsuario;
	private String idioma;
	private List<String> roles;
	private String token;
	private String type = "Bearer";


}
