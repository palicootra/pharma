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

@RestController
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


    @CrossOrigin(origins = "*")
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest utilisateur) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getUsername(),
                            utilisateur.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            e.printStackTrace();
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(utilisateur.getUsername());
        final String token =
                jwtUtility.generateToken(userDetails);
        Utilisateur logU = userService.findUser(utilisateur.getUsername());
        return new JwtResponse(token,logU);
    }
}
