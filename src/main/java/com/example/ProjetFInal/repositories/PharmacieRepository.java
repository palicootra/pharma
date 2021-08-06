package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Pharmacie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PharmacieRepository extends MongoRepository<Pharmacie,String> {

}
