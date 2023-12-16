package com.springteam.TechProduct.restcontroller;

import com.springteam.TechProduct.dto.LoginDTO;
import com.springteam.TechProduct.dto.LoginResponeDTO;
import com.springteam.TechProduct.dto.RegisterDTO;
import com.springteam.TechProduct.entity.Role;
import com.springteam.TechProduct.entity.UserEntity;
import com.springteam.TechProduct.securityconfig.JwtGenerator;
import com.springteam.TechProduct.service.RoleService;
import com.springteam.TechProduct.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserEntityService userEntityService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserEntityService userEntityService,
                          RoleService roleService,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator)
    {
        this.authenticationManager = authenticationManager;
        this.userEntityService = userEntityService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerInfo){

        //handle username and email is existing
        if(userEntityService.isExistsByUserName(registerInfo.getUserName()))
            return new ResponseEntity<>("exists username", HttpStatus.BAD_REQUEST);
        else if (userEntityService.isExistsByEmail(registerInfo.getEmail()))
            return new ResponseEntity<>("exists email", HttpStatus.BAD_REQUEST);

        UserEntity user = new UserEntity();

        //set username, password and email
        user.setUserName(registerInfo.getUserName());
        user.setPassWord(passwordEncoder.encode(registerInfo.getPassword()));
        user.setEmail(registerInfo.getEmail());

        //get user role in db
        Role role = roleService.getRoleByName("USER");

        //set new user role
        Set<Role> roles = new HashSet<>();

        //add user to db
        roles.add(role);
        user.setRoles(roles);

        userEntityService.addNewUser(user);

        return new ResponseEntity<>("success to register", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponeDTO> login(@RequestBody LoginDTO loginInfo){

        Authentication authentication = authenticationManager.authenticate
                                        (new UsernamePasswordAuthenticationToken(
                                                loginInfo.getUserName(),loginInfo.getPassword()
                                        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new LoginResponeDTO(token), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
