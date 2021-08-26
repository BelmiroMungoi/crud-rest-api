package com.bbm.crudrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbm.crudrestapi.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	@Query(value = "select u from UsuarioEntity u where upper(trim(u.nomeCompleto)) like %?1%")
	List<UsuarioEntity> findByName(String name);
}
