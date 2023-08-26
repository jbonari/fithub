package com.fithub.app.payload.response;

import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse{
	private String email;
	private Long idUsuario;
	private String idioma;
	private List<String> roles;
	private String token;
	private String type = "Bearer";




}
