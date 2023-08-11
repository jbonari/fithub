package com.fithub.app.repositoris;

import com.fithub.app.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	public Rol save(Rol rol);

	// READ
	public Optional<Rol> findByIdRol(Long idRol);

	public List<Rol> findAll();

	public Optional<Rol> findByNombre(String rol);

	// DELETE
	public void delete(Rol rol);



}
