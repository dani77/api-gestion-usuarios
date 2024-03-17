package com.desafio.apigestionusuarios.controller.demo;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.apigestionusuarios.entity.Users;
import com.desafio.apigestionusuarios.controller.generic.GenericController;
import com.desafio.apigestionusuarios.service.mantenimiento.UserService;
import com.desafio.apigestionusuarios.util.common.Response;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController extends GenericController{
	
	@Autowired
	private UserService userService;

	
	@GetMapping
	public ResponseEntity<Response> findLike() {
		try {
			return super.getListResponse(userService.findLike(null));	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return super.getInternalServerError(e);			
		}
	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		if (id < 0) {
			return super.getIdBadRequest(id);
		}
		try {
			Optional<Users> opt = userService.findById(Users.builder().id(id).build());
			if (!opt.isPresent()) {
				return super.getNotFound(id);
			}
			return ResponseEntity.ok(opt);
		} catch (Exception e) {
			
			return super.getInternalServerError(e);
		}
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Validated Users user, BindingResult result) {
		try {
			if (result.hasErrors()) {				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(super.getErrrors(result));
			}
			return ResponseEntity.ok(userService.insert(user));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return super.getInternalServerError(e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Users cliente) {
		if (id < 0) {
			return super.getIdBadRequest(id);
		}
		try {
			Optional<Users> optClienteEntity = userService.findById(Users.builder().id(id).build());
			if (!optClienteEntity.isPresent()) {
				return super.getNotFound(id);
			}

			cliente.setId(id);
			Users tmpClienteEntity = optClienteEntity.get();
			BeanUtils.copyProperties(cliente, tmpClienteEntity);
			return ResponseEntity.ok(userService.update(tmpClienteEntity));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return super.getInternalServerError(e);
		}
	}		

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete( @PathVariable Long id) {
		if (id < 0) {
			return super.getIdBadRequest(id);
		}
		try {
			Optional<Users> optClienteEntity = userService.findById(Users.builder().id(id).build());
			if (!optClienteEntity.isPresent()) {
				return super.getNotFound(id);
			}
			Users tmpClienteEntity = optClienteEntity.get();

			return ResponseEntity.ok(userService.delete(tmpClienteEntity));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return super.getInternalServerError(e);
		}
	}
	
}
