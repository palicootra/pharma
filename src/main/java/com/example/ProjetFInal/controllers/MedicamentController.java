package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.exceptions.SortieIntrouvaleException;
import com.example.ProjetFInal.modeles.*;
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
    private  final LotService lotService;
    private final PharmacieService pharmacieService;

    @Autowired
    public MedicamentController(MedicamentService medicamentService, TagService tagService, LotService lotService, PharmacieService pharmacieService) {
        this.medicamentService = medicamentService;
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
     * fonction principale de recherche
     *
     *
     * */
    @CrossOrigin(origins = "*")
    @GetMapping("/findMedoc")


    private ResponseEntity<Object>  findMedoc(@RequestParam String nom_medoc){

        //recherche du mot saisi dans la liste des medicaments
        List<Medicament> medicaments = medicamentService.getByName(nom_medoc);
        List<Medicament> medicaments2 = medicamentService.getByMarque(nom_medoc);
        medicaments.addAll(medicaments2);

        Set<Pharmacie> pharmacies = new HashSet<>();

            for (Medicament medoc:medicaments) {
                List<Lot> lots = lotService.getByIdMedicament(medoc.getId());
                for (Lot lot:lots) {
                    if(lot.getQte_lot()>0)
                     pharmacies.addAll(pharmacieService.getPharma(lot.getId_pharmacie()));

                     System.out.println(pharmacies);

                }
            }


        return new ResponseEntity<>(pharmacies, HttpStatus.OK) ;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findAllMedoc")
    public ResponseEntity  getAll(@RequestParam (required = false) String id_pharmacie){
        List<Medicament> medicaments;
        if(id_pharmacie!= null){
             medicaments = medicamentService.getByPharmacie(id_pharmacie);
        }else{
             medicaments = medicamentService.getAll();
        }

        HashSet<Pharmacie> pharmacies = new HashSet<>();
        for (Medicament medicament :medicaments) {
            if( medicament.getId_pharmacie() != null && medicament.getId_pharmacie().trim().length() != 0 ){
                Optional<Pharmacie> pharma = this.pharmacieService.getById(medicament.getId_pharmacie());
                pharma.ifPresent(pharmacies::add);
                //medicaments.add(this.medicamentService.getById(sortie.getId_medoc()).get()) ;
            }

        }
        ArrayList<Object> response = new ArrayList<>();
        response.add(medicaments);
        response.add(pharmacies);

        System.out.println(medicaments.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public long  count( String id_pharmacie){
        long total;
        if(id_pharmacie==null){
             total = medicamentService.getNumber();

        }else{
            List<Medicament> medicaments = medicamentService.getByPharmacie(id_pharmacie);
            total=medicaments.size();
        }
        return total;

    }


    @CrossOrigin(origins = "*")
    @DeleteMapping ("/delete")
    public ResponseEntity delete(@RequestBody Medicament medicament){
        try {
            System.out.println(medicament.toString());
            medicamentService.delete(medicament);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Optional<Medicament> med = this.medicamentService.getById(medicament.getId());
        Result resultat;
        if(!med.isPresent()){
            System.out.println("+++++++++++++++++++++++++++++++");

            resultat =new Result("supression effectué",202);
            System.out.println(medicament.toString());
        }else{
            resultat =new Result("Echec de supression",404);
        }


        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }

}
