package com.desafio.apigestionusuarios.util.common;

import java.time.LocalDateTime;
import com.desafio.apigestionusuarios.util.enums.CodeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

	private CodeEnum codigo;
	private String mensaje;
	 
	 
	@Builder.Default
	private LocalDateTime fechaHora = LocalDateTime.now();
}
