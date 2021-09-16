package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.modeles.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@CrossOrigin(origins = "*")

public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

     Utilisateur findByUsername(String username);

     @Query("{ 'id_pharma' : ?0 }")
     List<Utilisateur> findById_pharmacie(String id_pharmacie);
}
