package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

    @Query("{ 'username' : ?0 }")
     Utilisateur findByUsername(String username);

}
