package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.repositories.PharmacieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Optional<Pharmacie> getPharma(@RequestParam String id){
        return pharmacieRepository.findById(id);
    }
}
