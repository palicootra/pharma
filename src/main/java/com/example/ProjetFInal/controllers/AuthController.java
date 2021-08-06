package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.JwtRequest;
import com.example.ProjetFInal.modeles.JwtResponse;
import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.CategorieRepository;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import com.example.ProjetFInal.services.UserService;
import com.example.ProjetFInal.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;



    @GetMapping("/")
    public String auth(){
        return "welcome to us";
    }

    @GetMapping("/lol")
    public String authe(){
        return "welcome to lol";
    }





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

        return new  JwtResponse(token);
    }
}
