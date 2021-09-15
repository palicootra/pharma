package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.repositories.MedicamentReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentReposotory medicamentReposotory;

    public Medicament create(Medicament medicament){
        return medicamentReposotory.save(medicament);
    }
    public List<Medicament> getByName(String nom_medoc){return  medicamentReposotory.findByNom_medoc(nom_medoc);}
    public List<Medicament> getTag(String tag) {return medicamentReposotory.findAllByTag(tag);}
    public List<Medicament> getAll() {return medicamentReposotory.findAll();}
    public Optional<Medicament> getById(String id) {return medicamentReposotory.findById(id);}
    public Medicament save(Medicament medicament){return medicamentReposotory.save(medicament);}
    public void delete(Medicament medicament){
        System.out.println(medicament.toString());
        medicamentReposotory.delete(medicament);
    }


}
