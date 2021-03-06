package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.exceptions.SortieIntrouvaleException;
import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Sortie")
public class SortieController {

    private final SortieService sortieService;

    @Autowired
    public SortieController(SortieService sortieService) {
        this.sortieService = sortieService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addSortie")
    private String getall(@RequestBody Sortie sortie){
        Sortie sortie1 = sortieService.create(sortie);
        return sortie1.toString();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findSortie/{id}")
    private  ResponseEntity<?> getId(@PathVariable String id){
        Optional<Sortie> sortie = sortieService.getId(id);
        return new ResponseEntity<>(sortie, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getByPharmacie/{id_pharmacie}")
    private List<Sortie> findById_pharmacie(@PathVariable String id_pharmacie){
             List<Sortie> sortiepharma = sortieService.findById_pharmacie(id_pharmacie);
             if (sortiepharma.isEmpty()) throw new SortieIntrouvaleException("Le cette liste est vide aucune vente pour cete oharmacie ");
        return sortiepharma;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getByMedoc/{id_medoc}")
    private List<Sortie> findById_medoc(@PathVariable String id_medoc){
        return sortieService.findById_medoc(id_medoc);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findAllSortie")
    private List<Sortie> getAll(){
        return sortieService.getAll();
    }


    @CrossOrigin(origins = "*")
    @PutMapping("/updateSortie")
    public ResponseEntity updateSortie(@RequestBody Sortie sortie){

        Sortie sortie1 = sortieService.getId(sortie.getId())
                .orElseThrow(()->new RuntimeException(String.format("Nous trouvons aucune vente avec cette id  %s\"",sortie.getId())));
        sortie1.setDate_sort(sortie.getDate_sort());
        sortie1.setQte_sort(sortie.getQte_sort());
        sortie1.setId_medoc(sortie.getId_medoc());
        sortie1.setId_pharmacie(sortie.getId_pharmacie());
        sortieService.save(sortie1);
        return ResponseEntity.ok().build();
            }

    @CrossOrigin(origins = "*")
    @DeleteMapping ("/deleteSortie")
    public ResponseEntity<String> delete(@RequestParam String id_sort){
        sortieService.deleteSortie(id_sort);
        String supp = "supression effectu??";
        return new ResponseEntity<>(supp, HttpStatus.OK);
    }
}
