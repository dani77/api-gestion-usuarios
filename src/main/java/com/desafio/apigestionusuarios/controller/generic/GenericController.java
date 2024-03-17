package com.desafio.apigestionusuarios.controller.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.desafio.apigestionusuarios.util.common.Message;
import com.desafio.apigestionusuarios.util.common.Response;
import com.desafio.apigestionusuarios.util.enums.CodeEnum;

import static com.desafio.apigestionusuarios.util.enums.CodeEnum.ERROR;

public class GenericController {

	protected List<Map<String, String>> getErrrors(BindingResult result) {

		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());
		return errors;
	}
	
	protected ResponseEntity<Response> getInternalServerError(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				Response.builder().message(Message.builder()
								.codigo(ERROR)
								.mensaje(e.getMessage())
								.build())
					.build()	
				);
	}
	
	protected ResponseEntity<Response> getNoContent(){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	protected ResponseEntity<Response> getNotFound(){
		return this.getNotFound("No existen regitros");		
		
	}
	
	protected ResponseEntity<Response> getNotFound(Long id){
		return this.getNotFound("No existe registro con el id=" + id);
	}
	
	protected ResponseEntity<Response> getNotFound(Object msg){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response
				.builder()
				.message(Message.builder().codigo(CodeEnum.ALERTA).mensaje(msg.toString()).build())
		    .build());
	}
	
	
	protected ResponseEntity<Response> getOk(List<?> lst){		
		return 	ResponseEntity.ok(
					Response
						.builder()
							.message(Message.builder().codigo(CodeEnum.EXITO).mensaje("Exito de consulta").build())
							.response(lst)
					    .build());
	}
	
	protected ResponseEntity<Response>  getIdBadRequest(Long id){ 
		String mensaje=  String.format("id= %s no es válido, debe ser positivo",id);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response
				.builder()
				.message(Message.builder().codigo(CodeEnum.ALERTA).mensaje(mensaje).build())
		    .build());
	
	}
	
	protected ResponseEntity<List<Map<String,String>>>  getBadRequest(BindingResult result){ 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.getErrrors(result));
	}
	
	protected ResponseEntity<Response> getListResponse(List<?> lst) {
		if (lst.isEmpty()) {
			return this.getNotFound();
		} else {

			return this.getOk(lst);

		}
	}	

}
