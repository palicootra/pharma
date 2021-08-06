package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @GetMapping ("/all")
    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    @PostMapping("/createUser")
    public String create(@RequestBody Utilisateur utilisateur){
        Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
        return insertuser.toString();
    }



}
