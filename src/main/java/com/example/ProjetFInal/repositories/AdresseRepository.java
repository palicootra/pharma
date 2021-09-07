package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Adresse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends MongoRepository<Adresse ,String> {
    @Query("{ 'code_postal' : ?0 }")
    Adresse findByCode_postal(String code_postal);
}
