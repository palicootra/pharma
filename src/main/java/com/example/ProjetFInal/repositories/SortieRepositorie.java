package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Sortie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SortieRepositorie extends MongoRepository<Sortie,String> {
}
