package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    public UtilisateurRepository utilisateurRepository;

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
    public ResponseEntity<?> test(){
        String response = "test ok";

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
