package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.repositories.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {
    public final SortieRepository sortieRepository;

    @Autowired
    public SortieService(SortieRepository sortieRepository) {
        this.sortieRepository = sortieRepository;
    }

    public Sortie create(Sortie sortie){
        return sortieRepository.save(sortie);
    }
    public Sortie save(Sortie sortie){return sortieRepository.save(sortie);}
    public Optional<Sortie> getId(String id_sort){return sortieRepository.findById(id_sort);}
    public void deleteSortie(String id_sort){
        sortieRepository.deleteById(id_sort);}
    public List<Sortie> findById_medoc(String id_medoc){return sortieRepository.findById_medoc(id_medoc);}
    public List<Sortie> findById_pharmacie(String id_pharmacie){return sortieRepository.findById_pharmacie(id_pharmacie);}
    public List<Sortie> getAll(){return sortieRepository.findAll();}

    public long count(){return  sortieRepository.count();}



}
