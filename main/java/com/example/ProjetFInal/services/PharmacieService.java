package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.repositories.PharmacieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacieService {

    @Autowired
    private PharmacieRepository pharmacieRepository;

    public Pharmacie create(Pharmacie pharmacie){
        return  pharmacieRepository.insert(pharmacie);
    }
    public List<Pharmacie> getAll(){return pharmacieRepository.findAll();}
    public void delePharma( String id_pharma){ pharmacieRepository.deleteById(id_pharma);}
    public List<Pharmacie> getPharma( String id){
        return pharmacieRepository.findPharmacieById(id);
    }
    public Pharmacie save(Pharmacie pharmacie){return pharmacieRepository.save(pharmacie);}
    public Optional<Pharmacie> getId( String id_pharma){return pharmacieRepository.findById(id_pharma);}
}
