package com.example.ProjetFInal.controllers;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins ="http://localhost:4200")
    @PostMapping("/addLot")
    private String create( @RequestBody  Lot lot){
        //qte doit prendre qte de depart
        Lot lot1 = lotService.create(lot);
        return lot1.toString();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getLot")
    private Lot getNum(String num_lot){
        return lotService.getNum(num_lot);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allLot")
    private List<Lot> gatAll(){return lotService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allPharmaLot")
    private List<Lot> getByIdPharma(@PathVariable String id_pharma){
        return lotService.getByIdPharma(id_pharma);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allMedicamentLot")
    private List<Lot> getBiIdMedicament(@PathVariable String id_medicament){
        return lotService.getBiIdMedicament(id_medicament);
    }
}
