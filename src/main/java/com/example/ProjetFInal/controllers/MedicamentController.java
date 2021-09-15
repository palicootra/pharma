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
import com.example.ProjetFInal.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private ResponseEntity create(@RequestBody Medicament medicament){
        medicament.setCreated_at(new Date());
        Medicament medicament1 = medicamentService.create(medicament);
        return new ResponseEntity<>(medicament1, HttpStatus.OK);
    }

    /**
     * foncttion principale de recherche
     *
     *
     * */
    @CrossOrigin(origins = "*")
    @GetMapping("/findMedoc")


    private ResponseEntity<Object>  findMedoc(@RequestParam String nom_medoc){

        //recherche du mot saisi dans la liste des medicaments
        List<Medicament> medicaments = medicamentService.getByName(nom_medoc);

        Set<Pharmacie> pharmacies = new HashSet<>();
        if (medicaments.isEmpty()){
            Tag tag=tagService.getByLibele(nom_medoc);
            if (tag == null){
                if (medicaments.isEmpty()) throw new SortieIntrouvaleException("Aucune maladie ou medicament ne correspond a ce mot clé ");

            }else {
                medicaments=medicamentService.getTag(tag.getId_tag());
                for (Medicament medoc:medicaments
                ) {
                    List<Lot> lots = lotService.getBiIdMedicament(medoc.getId());
                    for (Lot lot:lots
                    ) {
                        //pharmacies = pharmacieService.getPharma(lot.getId_pharmacie());
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
                     pharmacies.addAll(pharmacieService.getPharma(lot.getId_pharmacie()));

                     System.out.println(pharmacies);

                }
            }

        }
        return new ResponseEntity<>(pharmacies, HttpStatus.OK) ;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findAllMedoc")
    public ResponseEntity  getAll(){
        List<Medicament> medicaments = medicamentService.getAll();
        System.out.println(medicaments.size());
        return new ResponseEntity<>(medicaments, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping ("/delete")
    public ResponseEntity<Result> delete(@RequestBody Medicament medicament){
        try {
            System.out.println(medicament.toString());
            medicamentService.delete(medicament);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Optional<Medicament> med = this.medicamentService.getById(medicament.getId());
        Result resultat;
        if(!med.isPresent()){
            resultat =new Result("supression effectué",202);
        }else{
            resultat =new Result("Echec de supression",404);
        }


        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }

}
