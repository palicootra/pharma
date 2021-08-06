package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Adresse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdresseRepository extends MongoRepository<Adresse ,String> {
}
