package com.fithub.app.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AltaUsuarioRequest {

    @NotBlank
    @Email
    @Size(min = 4,max = 100)
    private String email;

    @NotNull
    @Size(max = 120)
    private String password;

    @NotNull
    @Email
    @Size(max = 100)
    private String nombre;

    @NotNull
    @Email
    @Size(max = 150)
    private String apellidos;

    @NotNull
    @Size(max = 6)
    private String idioma;

    @NotNull
    @Size(max = 45)
    private Set<String> roles;
    
    //private Empresa empresa;

    
}
