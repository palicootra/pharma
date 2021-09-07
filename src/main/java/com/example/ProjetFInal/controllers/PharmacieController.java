package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.services.PharmacieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pharmacie")
public class PharmacieController {
    @Autowired
    private PharmacieService pharmacieService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addPharma")
    public  ResponseEntity create(@RequestBody Pharmacie pharmacie){
        Pharmacie pharmacie1 = pharmacieService.create(pharmacie);
        return new ResponseEntity<>(pharmacie1, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/findAll")
    public ResponseEntity<List<Pharmacie>> getAll(){
        List<Pharmacie> pharmacies = pharmacieService.getAll();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }



}
