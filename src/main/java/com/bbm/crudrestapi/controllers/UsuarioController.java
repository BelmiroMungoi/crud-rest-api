package com.bbm.crudrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bbm.crudrestapi.model.UsuarioEntity;
import com.bbm.crudrestapi.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping(value = "/listar")
	@ResponseBody
	public ResponseEntity<List<UsuarioEntity>> listaUsuario() {

		List<UsuarioEntity> lista = repository.findAll();

		// Retorna uma lista dos dados no formato JSON
		return new ResponseEntity<List<UsuarioEntity>>(lista, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario) {

		UsuarioEntity user = repository.save(usuario);

		return new ResponseEntity<UsuarioEntity>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deletar")
	@ResponseStatus
	public ResponseEntity<String> deleta(@RequestParam Long id) {

		repository.deleteById(id);

		return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarusuario")
	@ResponseStatus
	public ResponseEntity<UsuarioEntity> buscar(@RequestParam(name = "id") Long id) {

		UsuarioEntity entity = repository.findById(id).get();

		return new ResponseEntity<UsuarioEntity>(entity, HttpStatus.OK);
	}

	@PutMapping(value = "/atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody UsuarioEntity usuario) {

		if (usuario.getId() == null) {

			return new ResponseEntity<String>("Id do usuario não foi informado", HttpStatus.OK);

		} else {
			UsuarioEntity user = repository.saveAndFlush(usuario);

			return new ResponseEntity<UsuarioEntity>(user, HttpStatus.OK);
		}

	}
}
