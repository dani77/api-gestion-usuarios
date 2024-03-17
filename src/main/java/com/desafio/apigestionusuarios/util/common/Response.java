package com.desafio.apigestionusuarios.util.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

	private Message message;
	private Object  response;//recibe la respuesta

}
