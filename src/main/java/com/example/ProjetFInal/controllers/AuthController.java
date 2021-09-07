package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.JwtRequest;
import com.example.ProjetFInal.modeles.JwtResponse;
import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.services.UserService;
import com.example.ProjetFInal.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController("/auth")
public class AuthController {


    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @Autowired
    public AuthController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;

    }

    @GetMapping("/")
    public String auth(){
        return "welcome to us";
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw  new Exception("INVALID_CREDENTIAL",e);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token =
                jwtUtility.generateToken(userDetails);
Utilisateur logU = userService.findUser(jwtRequest.getUsername());
        return new  JwtResponse(token,logU);
    }
}
