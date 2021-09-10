package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.exceptions.SortieIntrouvaleException;
import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Result;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.services.LotService;
import com.example.ProjetFInal.services.MedicamentService;
import com.example.ProjetFInal.services.PharmacieService;
import com.example.ProjetFInal.services.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Sortie")
public class SortieController {

    private final SortieService sortieService;
    private final LotService lotService;
    private final MedicamentService medicamentService;
    private final PharmacieService pharmacieService;


    @Autowired
    public SortieController(SortieService sortieService,LotService lotService, PharmacieService pharmacieService,
                            MedicamentService medicamentService) {
        this.sortieService = sortieService;
        this.lotService = lotService;
        this.medicamentService = medicamentService;
        this.pharmacieService = pharmacieService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addSortie")
    private ResponseEntity<Object> addSortie(@RequestBody Sortie sortie){
        int quantity = 0;
        List <String>id_lots= new ArrayList<>();
        System.out.println(sortie.getId_medoc());
        //Medicament medicament = this.medicamentService.getById(sortie.getId_medoc()).get();
        List<Lot> lots = this.lotService.getBiIdMedicament(sortie.getId_medoc());
        System.out.println(lots.size() );



        for (int i = 0; i <sortie.getQte_sort(); i++) {
            if(lots.size()> 0){
                Lot lot1 = lots.get(0);
                lot1.setQte_lot(lot1.getQte_lot()-1);
                id_lots.add(lot1.getId());
                sortie.setTotal_amount(lot1.getPrix_lot()+sortie.getTotal_amount());

                try {
                    lots.removeIf(lot -> !(lot.getQte_lot() > 0));
                }catch (Exception e){
                    e.printStackTrace();
                }
                quantity=quantity+1;
            }
        }
        System.out.println(quantity);
        sortie.setId_lots(id_lots);
        if(quantity==sortie.getQte_sort()){
            sortie.setDate_sort(new Date());
            sortie.setUnit_price(sortie.getTotal_amount()/quantity);
            Sortie sortie1 = sortieService.create(sortie);
            for (Lot lot:lots) {
                this.lotService.save(lot);
            }
            return new ResponseEntity(sortie1,HttpStatus.OK) ;
        }else {
            Result resultat = new Result("le stock actuel n'est pas suffisant pour effectuer cette vente", 406);
            return new ResponseEntity(resultat,HttpStatus.OK) ;
        }




        //Sortie sortie1 = sortieService.create(sortie);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findSortie/{id}")
    private  ResponseEntity<?> getId(@PathVariable String id){
        Optional<Sortie> sortie = sortieService.getId(id);
        return new ResponseEntity<>(sortie, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getByPharmacie/{id_pharmacie}")
    private ResponseEntity<Object> findById_pharmacie(@PathVariable String id_pharmacie){
        List<Sortie> sorties = sortieService.findById_pharmacie(id_pharmacie);
        HashSet<Medicament> medicaments = new HashSet<>();
        for (Sortie sortie :sorties) {
            if( sortie.getId_medoc() != null && sortie.getId_medoc() .trim().length() != 0 ){
                medicaments.add(this.medicamentService.getById(sortie.getId_medoc()).get()) ;
            }

        }
        ArrayList<Object> response = new ArrayList<>();
        response.add(sorties);
        response.add(medicaments);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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


    @CrossOrigin(origins = "http://localhost:4200")
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

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping ("/deleteSortie")
    public ResponseEntity<String> delete(@RequestParam String id_sort){
        sortieService.deleteSortie(id_sort);
        String supp = "supression effectué";
        return new ResponseEntity<>(supp, HttpStatus.OK);
    }
}
