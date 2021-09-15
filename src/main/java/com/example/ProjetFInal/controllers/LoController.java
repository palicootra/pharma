package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.services.LotService;
import com.example.ProjetFInal.services.MedicamentService;
import com.example.ProjetFInal.services.PharmacieService;
import com.example.ProjetFInal.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/lot")
public class LoController {
    private final LotService lotService;
    private final MedicamentService medicamentService;
    private final PharmacieService pharmacieService;

    @Autowired
    public LoController(LotService lotService, PharmacieService pharmacieService,
                        MedicamentService medicamentService) {
        this.lotService = lotService;
        this.medicamentService = medicamentService;
        this.pharmacieService = pharmacieService;
    }

    @CrossOrigin(origins ="*")
    @PostMapping("/addLot")
    private ResponseEntity <Object>create( @RequestBody  Lot lot){
        lot.setCreated_at(new Date());
        lot.setQte_lot(lot.getQtedepart_lot());

        Medicament medicament = this.medicamentService.getById( lot.getId_medicament()).get();
        System.out.println(medicament.getNom_medoc());
        medicament.setLast_price(lot.getPrix_lot());
        medicamentService.save(medicament);
        Lot lot1 = lotService.create(lot);
        return new ResponseEntity<>(lot1, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/getLot")
    private Lot getNum(String num_lot){
        return lotService.getNum(num_lot);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allLot")
    private ResponseEntity<Object> gatAll(){
        List<Lot> lots = lotService.getAll();
        HashSet<Pharmacie> pharmacies = new HashSet<>();
        for (Lot lot :lots) {
            if( lot.getId_pharmacie() != null && lot.getId_pharmacie().trim().length() != 0 ){
                System.out.println(lot.getId_pharmacie());
              //  pharmacies.add(this.pharmacieService.getPharma(lot.getId_pharmacie()).get(0)) ;
            }

        }
        HashSet<Medicament> medicaments = new HashSet<>();
        for (Lot lot :lots) {
            if( lot.getId_medicament() != null && lot.getId_medicament().trim().length() != 0 ){
                System.out.println(lot.getId_medicament());
                Optional<Medicament> med = this.medicamentService.getById(lot.getId_medicament());
                med.ifPresent(medicaments::add);

            }

        }
        ArrayList<Object> response = new ArrayList<>();
        response.add(lots);
        response.add(pharmacies);
        response.add(medicaments);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allPharmaLot")
    private List<Lot> getByIdPharma(@PathVariable String id_pharma){
        return lotService.getByIdPharma(id_pharma);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/allMedicamentLot")
    private List<Lot> getByIdMedicament(@PathVariable String id_medicament){
        return lotService.getByIdMedicament(id_medicament);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateLot")
    public ResponseEntity updateLot(@RequestBody Lot lot){
        Lot lot1 = lotService.getID(lot.getId())
                .orElseThrow(()->new RuntimeException(String.format("\"nous ne trouvons pas de lot avec  pour l'id: %s\"",lot.getId())));
        lot1.setNum_lot(lot.getNum_lot());
        lot1.setQte_lot(lot.getQte_lot());
        lot1.setDatein_lot(lot.getDatein_lot());
        lot1.setQtedepart_lot(lot.getQtedepart_lot());
        lot1.setQtefab_lot(lot.getQtefab_lot());
        lot1.setPrix_lot(lot.getPrix_lot());
        lot1.setId_medicament(lot.getId_medicament());
        lot1.setId_pharmacie(lot.getId_pharmacie());
        lot1.setId_utilisateur(lot.getId_utilisateur());
        lotService.save(lot1);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping ("/delete")
    public ResponseEntity<Result> delete(@RequestBody Lot lot){
        lotService.deleteLot(lot.getId());
        Result resultat =new Result("supression effectué",202);
        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }


}
