package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Pharmacien;
import com.example.ProjetFInal.modeles.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhamacienRepository extends MongoRepository<Pharmacien,String> {
}
