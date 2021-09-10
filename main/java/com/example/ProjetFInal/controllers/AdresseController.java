package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Adresse;
import com.example.ProjetFInal.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdresseController {

    @Autowired
    private AdresseRepository adresseRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/addAdresse")
    public String create(@RequestBody Adresse adresse){
        Adresse adresse1 = adresseRepository.insert(adresse);
        return adresse1.toString();
    }

}
