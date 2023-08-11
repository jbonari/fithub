package com.fithub.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rol")
//@Audited
public class Rol implements Serializable {

	private static final long serialVersionUID = -7750735958569819018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;

	@Size(max= 45)
	private String nombre;



}
