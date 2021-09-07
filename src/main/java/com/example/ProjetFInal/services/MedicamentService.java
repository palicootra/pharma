package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.repositories.MedicamentReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentReposotory medicamentReposotory;

    public Medicament create(Medicament medicament){
        return medicamentReposotory.insert(medicament);
    }
    public List<Medicament> getName(String nom_medoc){return  medicamentReposotory.findByNom_medoc(nom_medoc);}
    public List<Medicament> getTag(String tag) {return medicamentReposotory.findAllByTag(tag);}
    public List<Medicament> getAll() {return medicamentReposotory.findAll();}

}
