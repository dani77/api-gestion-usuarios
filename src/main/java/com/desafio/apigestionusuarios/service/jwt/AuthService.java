package com.desafio.apigestionusuarios.service.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.desafio.apigestionusuarios.dto.AuthResponse;
import com.desafio.apigestionusuarios.dto.LoginRequest;
import com.desafio.apigestionusuarios.dto.RegisterRequest;
import com.desafio.apigestionusuarios.entity.Users;
import com.desafio.apigestionusuarios.repository.UsersRepository;
import com.desafio.apigestionusuarios.util.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsersRepository userRepository;
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(),request.getPassword()));
		Users user = userRepository.findByName(request.getName()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}
	
	public AuthResponse register(RegisterRequest request) {
		Users user = Users.builder()
				.name(request.getName())				
				.password(request.getPassword())
				.role(Role.USER)
				.build();
				
		userRepository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

}
