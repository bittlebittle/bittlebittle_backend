package com.spring.bittlebittle.utils1;

import org.springframework.stereotype.Repository;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	 User findByUsername(String username);
	 
	
}
