package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@Repository
@CrossOrigin(origins = "*")

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

     Utilisateur findByUsername(String username);
}
