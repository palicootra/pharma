package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.JwtRequest;
import com.example.ProjetFInal.modeles.JwtResponse;
import com.example.ProjetFInal.modeles.Result;
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

import java.util.ArrayList;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/createUser")
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur){
       try {
           utilisateur.setEtatuser(1);
           utilisateur.setSatutuser(true);
           utilisateur.getRoles().add("USER");

           Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
           return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
       }catch (Exception e){
           e.printStackTrace();
           Result response = new Result("cet utilisateur existe déja", 409);

           return new ResponseEntity<>(response, HttpStatus.CONFLICT);
       }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public  ResponseEntity authenticate(@RequestBody Utilisateur utilisateur) throws BadCredentialsException {
        try {
            System.out.println(utilisateur.getUsername());
            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getUsername(),
                            utilisateur.getPassword()
                    )

            );
        }catch (BadCredentialsException e){
            e.printStackTrace();
            return new ResponseEntity<>(new Result("bad credentials",401), HttpStatus.UNAUTHORIZED);
           // return new JwtResponse(null,null);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(utilisateur.getUsername());
        final String token =
                jwtUtility.generateToken(userDetails);
        Utilisateur logU = userService.findUser(utilisateur.getUsername());
        return new ResponseEntity<>(new JwtResponse(token,logU), HttpStatus.OK);

        //return new ResponseEntity<>(token, HttpStatus.OK);

    }



}
