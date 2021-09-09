package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.Tag;
import com.example.ProjetFInal.services.CatService;
import com.example.ProjetFInal.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addTag")
    private String create(@RequestBody Tag tag){
        Tag tag1 = tagService.create(tag);
        return tag1.toString();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAllTag")
    private List<Tag> getAll(){return tagService.getAll();}
}
