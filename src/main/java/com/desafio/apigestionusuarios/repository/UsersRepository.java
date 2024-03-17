package com.desafio.apigestionusuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.desafio.apigestionusuarios.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{

	public Optional<Users>findByName(String name);
}
