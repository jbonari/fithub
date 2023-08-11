package com.fithub.app.payload.response;

import lombok.*;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class JwtResponse {
	private String token;
	private Long idUsuario;

}
