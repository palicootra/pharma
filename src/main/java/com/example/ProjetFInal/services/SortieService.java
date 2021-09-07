package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.repositories.SortieRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {
    public final SortieRepositorie sortieRepositorie;

    @Autowired
    public SortieService(SortieRepositorie sortieRepositorie) {
        this.sortieRepositorie = sortieRepositorie;
    }

    public Sortie create(Sortie sortie){
        return sortieRepositorie.save(sortie);
    }

    public Sortie getId(String id){return sortieRepositorie.findSortiesBy(id);}

    public List<Sortie> findById_medoc(String id_medoc){return sortieRepositorie.findById_medoc(id_medoc);}
    public List<Sortie> findById_pharmacie(String id_pharmacie){return sortieRepositorie.findById_pharmacie(id_pharmacie);}
    public List<Sortie> getAll(){return sortieRepositorie.findAll();}
}
