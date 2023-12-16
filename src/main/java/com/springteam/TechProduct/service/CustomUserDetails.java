package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.Role;
import com.springteam.TechProduct.entity.UserEntity;
import com.springteam.TechProduct.exception.UserNotFoundException;
import com.springteam.TechProduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//read user from database
@Service
public class CustomUserDetails implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public CustomUserDetails(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserEntity user = repository.findByUserName(username).orElseThrow(() -> new UserNotFoundException("username is not found"));

        return new User(user.getUserName(), user.getPassWord(), mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

}
