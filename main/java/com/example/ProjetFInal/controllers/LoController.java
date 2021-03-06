package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lot")
public class LoController {
    private final LotService lotService;

    @Autowired
    public LoController(LotService lotService) {
        this.lotService = lotService;
    }

    @CrossOrigin(origins ="*")
    @PostMapping("/addLot")
    private String create( @RequestBody  Lot lot){
        Lot lot1 = lotService.create(lot);
        return lot1.toString();
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/getLot")
    private Lot getNum(String num_lot){
        return lotService.getNum(num_lot);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allLot")
    private List<Lot> gatAll(){return lotService.getAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allPharmaLot")
    private List<Lot> getByIdPharma(@PathVariable String id_pharma){
        return lotService.getByIdPharma(id_pharma);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/allMedicamentLot")
    private List<Lot> getBiIdMedicament(@PathVariable String id_medicament){
        return lotService.getBiIdMedicament(id_medicament);
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
    @DeleteMapping ("/deleteLot")
    public ResponseEntity<String> delete(@RequestParam String id_lot){
        lotService.deleteLot(id_lot);
        String supp = "supression effectu??";
        return new ResponseEntity<>(supp, HttpStatus.OK);
    }
}
