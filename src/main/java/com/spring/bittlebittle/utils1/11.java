package com.spring.bittlebittle.utils1;

public interface UserDetails {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
