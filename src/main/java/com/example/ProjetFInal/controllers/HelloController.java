package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.JwtRequest;
import com.example.ProjetFInal.modeles.JwtResponse;
import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import com.example.ProjetFInal.services.UserService;
import com.example.ProjetFInal.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    @Autowired
    public UtilisateurRepository utilisateurRepository;

    public HelloController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService) {

            this.jwtUtility = jwtUtility;
            this.authenticationManager = authenticationManager;
            this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping ("/all")
    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createUser")
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur){
        Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
        return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public  ResponseEntity<?> authenticate() {



        //return new JwtResponse(token,logU);
        return new ResponseEntity<>("ggggggggggg", HttpStatus.OK);

    }



}
