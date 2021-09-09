package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.repositories.SortieRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Sortie save(Sortie sortie){return sortieRepositorie.save(sortie);}
    public Optional<Sortie> getId(String id_sort){return sortieRepositorie.findById(id_sort);}
    public void deleteSortie(String id_sort){sortieRepositorie.deleteById(id_sort);}
    public List<Sortie> findById_medoc(String id_medoc){return sortieRepositorie.findById_medoc(id_medoc);}
    public List<Sortie> findById_pharmacie(String id_pharmacie){return sortieRepositorie.findById_pharmacie(id_pharmacie);}
    public List<Sortie> getAll(){return sortieRepositorie.findAll();}

}
