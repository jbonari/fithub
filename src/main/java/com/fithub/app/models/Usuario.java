package com.fithub.app.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
//@Audited
public class Usuario implements UserDetails{

	//private static final long serialVersionUID = 261908593234919580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Long idUsuario;


	@Size(max = 120)
	@Column(name = "contrasenya")
	private String contrasenya;

	@Size(max = 120)
	@Column(name = "nombre")
	private String nombre;

	@Size(max = 120)
	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "email")
	private String email;

	@Size(max = 6)
	@Column(name = "idioma")
	private String idioma;

	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;

	@Column(name = "delete")
	private boolean delete;

	@ManyToMany
	@JoinTable(name = "rolusuario", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idrol"))
	private Set<Rol> roles = new HashSet<>();

	public Usuario(String contrasenya, String email, String idioma) {
		this.contrasenya = contrasenya;
		this.email = email;
		this.idioma= idioma;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
	}


	@Override
	public String getPassword() {
		return contrasenya;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
