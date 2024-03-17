package com.desafio.apigestionusuarios.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.desafio.apigestionusuarios.util.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Users")
@Table(name="USERS")
public class Users implements UserDetails{
	
	@Id 
	@Column(name="ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsers")
	@SequenceGenerator(sequenceName = "SEQ_USER", allocationSize = 1, name = "seqUser")
	private Long id;	
	
	@Column(name="NAME", nullable=false)
	private String name;	
		
	@Column(name="EMAIL")
	private String email;	
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="PHONES")
	private String phones;
	
	@Column(name="CREATED")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate created;
	
	@Column(name="MODIFIED")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate modified;
	
	@Column(name="LASTLOGIN")
	private String lastlogin;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="ISACTIVE")
	private String isactive;
	
	@Enumerated(EnumType.STRING)
	Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return List.of(new SimpleGrantedAuthority((role.name())));
	}

	@Override
	public String getUsername() {		
		return null;
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
