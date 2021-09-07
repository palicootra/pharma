package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Categorie")
public class CatController {

    @Autowired
    private CatService catService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addCat")
    private String create(@RequestBody Categories categories){
        Categories categories1 = catService.create(categories);
        return categories1.toString();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAllCat")
    private List<Categories> getAll(){
        List<Categories> alCat=  catService.getAll();

        return alCat;
    }
}
