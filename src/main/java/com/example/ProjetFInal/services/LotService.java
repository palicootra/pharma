package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.repositories.Lotrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {
    private  final Lotrepository lotrepository;

    @Autowired
    public LotService(Lotrepository lotrepository) {
        this.lotrepository = lotrepository;
    }
    public Lot create(Lot lot){
        return lotrepository.insert(lot) ;
    }

    public Lot getNum(String num_lot){return  lotrepository.findByNum_lot(num_lot);}

    public List<Lot> getByIdPharma(String id_pharma){
        return lotrepository.findById_pharmacie(id_pharma);
    }

    public List<Lot> getBiIdMedicament(String id_medicament){
        return lotrepository.findById_medicament(id_medicament);
    }

    public List<Lot> getByIdMedico(String id_medico){
        return lotrepository.findAllById_medicament(id_medico);
    }
    public List<Lot> getAll(){return  lotrepository.findAll();}

}
