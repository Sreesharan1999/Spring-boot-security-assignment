package com.springsecurity.springBootSecurity.pojo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
	
	Optional<AppUser> findByName( String name );

}
