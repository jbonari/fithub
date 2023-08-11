package com.fithub.app.repositoris;

import com.fithub.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@SuppressWarnings("unchecked")
	@Override
	public Usuario save(Usuario user);

	public Optional<Usuario> findByIdUsuario(Long idUsuario);
	
	@Override
	public List<Usuario> findAll();
	
	public Optional<Usuario> findByEmail(String email);
	
	public void delete(Usuario user);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByContrasenya(String contrasenya);


}
