package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Pharmacie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface MedicamentReposotory extends MongoRepository<Medicament,String> {

    @Query("{ 'nom_medoc' : ?0 }")
    List<Medicament> findByNom_medoc(String nom_medoc);


List<Medicament >findAllByTag(String id_tag);




}
