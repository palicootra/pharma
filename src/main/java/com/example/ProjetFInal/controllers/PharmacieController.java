package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.services.PharmacieService;
import com.example.ProjetFInal.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pharmacie")
public class PharmacieController {
    @Autowired
    private PharmacieService pharmacieService;

    @CrossOrigin(origins = "*")
    @PostMapping("/addPharma")
    public  ResponseEntity create(@RequestBody Pharmacie pharmacie){
        pharmacie.setCreated_at(new Date());
        Pharmacie pharmacie1 = pharmacieService.create(pharmacie);
        return new ResponseEntity<>(pharmacie1, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findAll")
    public ResponseEntity<List<Pharmacie>> getAll(){
        List<Pharmacie> pharmacies = pharmacieService.getAll();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updatePharma")
    public ResponseEntity updatePharma(@RequestBody Pharmacie pharmacie){
        Pharmacie pharmacie1 = pharmacieService.getId(pharmacie.getId())
                .orElseThrow(()-> new RuntimeException(String.format("nous ne trouvons pas de pharmacie avec  pour id: %s",pharmacie.getId())));
        pharmacie1.setNom_phar(pharmacie.getNom_phar());
        pharmacie1.setEmail_phar(pharmacie.getEmail_phar());
        pharmacie1.setTel_phar(pharmacie.getTel_phar());
        pharmacie1.setStatu_phar(pharmacie.getStatu_phar());
        pharmacie1.getAdresse().setPays(pharmacie.getAdresse().getPays());
        pharmacie1.getAdresse().setVille(pharmacie.getAdresse().getVille());
        pharmacie1.getAdresse().setRegion(pharmacie.getAdresse().getRegion());
        pharmacie1.getAdresse().setDepartement(pharmacie.getAdresse().getDepartement());
        pharmacie1.getAdresse().setLongitude(pharmacie.getAdresse().getLongitude());
        pharmacie1.getAdresse().setLatitude(pharmacie.getAdresse().getLatitude());
        pharmacie1.getAdresse().setCode_postal(pharmacie.getAdresse().getCode_postal());
        pharmacieService.save(pharmacie1);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping ("/deletePharma")
    public ResponseEntity<Result> delete(@RequestParam String id_pharma){
        pharmacieService.delePharma(id_pharma);
        Result resultat =new Result("supression effectué",204);

        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }



}
