package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.modeles.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")

public interface PharmacieRepository extends MongoRepository<Pharmacie,String> {
    @Query("{'nom_phar' : ?0 }")
    Pharmacie findByNom_phar(String nom_phar);
    List<Pharmacie> findPharmacieById(String id);
}
