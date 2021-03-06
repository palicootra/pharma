package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.exceptions.SortieIntrouvaleException;
import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.modeles.Tag;
import com.example.ProjetFInal.services.LotService;
import com.example.ProjetFInal.services.MedicamentService;
import com.example.ProjetFInal.services.PharmacieService;
import com.example.ProjetFInal.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicament")
public class MedicamentController {


    private final MedicamentService medicamentService;
    private final TagService tagService;
    private  final LotService lotService;
    private final PharmacieService pharmacieService;

    @Autowired
    public MedicamentController(MedicamentService medicamentService, TagService tagService, LotService lotService, PharmacieService pharmacieService) {
        this.medicamentService = medicamentService;
        this.tagService = tagService;
        this.lotService = lotService;
        this.pharmacieService = pharmacieService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addMedoc")
    private String create(@RequestBody Medicament medicament){
        Medicament medicament1 = medicamentService.create(medicament);
        return medicament1.toString();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findMedoc")
    private ResponseEntity<List<Pharmacie>>  findMedoc(@RequestParam String nom_medoc){
        List<Medicament> medicaments = medicamentService.getName(nom_medoc);
        List<Pharmacie> pharmacies = null;
        if (medicaments.isEmpty()){
            Tag tag=tagService.getByLibele(nom_medoc);
            if (tag == null){
                if (medicaments.isEmpty()) throw new SortieIntrouvaleException("Aucune maladie ou medicament ne correspond a ce mot cl?? ");

            }else {
                medicaments=medicamentService.getTag(tag.getId_tag());
                for (Medicament medoc:medicaments
                ) {
                    List<Lot> lots = lotService.getBiIdMedicament(medoc.getId());
                    for (Lot lot:lots
                    ) {
                        pharmacies = pharmacieService.getPharma(lot.getId_pharmacie());
                        System.out.println(pharmacies);

                    }
                }
            }


            return new ResponseEntity<>(pharmacies, HttpStatus.OK) ;
        }else {
            for (Medicament medoc:medicaments
            ) {
                List<Lot> lots = lotService.getBiIdMedicament(medoc.getId());
                for (Lot lot:lots
                ) {
                     pharmacies = pharmacieService.getPharma(lot.getId_pharmacie());
                     System.out.println(pharmacies);
                }
            }
            return new ResponseEntity<>(pharmacies, HttpStatus.OK) ;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findAllMedoc")
    private List<Medicament> getAll(String tag){return medicamentService.getTag( tag);}

}
