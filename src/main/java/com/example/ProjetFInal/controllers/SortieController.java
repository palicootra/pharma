package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.exceptions.SortieIntrouvaleException;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Sortie")
public class SortieController {

    private final SortieService sortieService;

    @Autowired
    public SortieController(SortieService sortieService) {
        this.sortieService = sortieService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addSortie")
    private String getall(@RequestBody Sortie sortie){
        Sortie sortie1 = sortieService.create(sortie);
        return sortie1.toString();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findSortie/{id}")
    private  ResponseEntity<Sortie> getId(@PathVariable String id){
        Sortie sortie = sortieService.getId(id);
        return new ResponseEntity<>(sortie, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getByPharmacie/{id_pharmacie}")
    private List<Sortie> findById_pharmacie(@PathVariable String id_pharmacie){
             List<Sortie> sortiepharma = sortieService.findById_pharmacie(id_pharmacie);
             if (sortiepharma.isEmpty()) throw new SortieIntrouvaleException("Le cette liste est vide aucune vente pour cete oharmacie ");
        return sortiepharma;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getByMedoc/{id_medoc}")
    private List<Sortie> findById_medoc(@PathVariable String id_medoc){
        return sortieService.findById_medoc(id_medoc);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAllSortie")
    private List<Sortie> getAll(){
        return sortieService.getAll();
    }
}
