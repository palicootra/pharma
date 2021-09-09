package com.example.ProjetFInal.services;

import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.Tag;
import com.example.ProjetFInal.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag create(Tag tag){
        return tagRepository.insert(tag);
    }
    public List<Tag> getAll(){return tagRepository.findAll();}
    public Tag getByLibele( String libele){return tagRepository.findByLibele(libele);}
}
