package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CategorieRepository categorieRepository;

    public Categories create(Categories categories){
        return categorieRepository.insert(categories);
    }
    public List<Categories> getAll(){return categorieRepository.findAll();}
}
