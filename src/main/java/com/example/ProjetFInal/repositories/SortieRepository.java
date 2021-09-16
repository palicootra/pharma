package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Lot;
import com.example.ProjetFInal.modeles.Sortie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SortieRepository extends MongoRepository<Sortie,String> {

    Sortie findSortiesBy(String id);

    @Query("{ 'id_medoc' : ?0 }")
    List<Sortie>findById_medoc(String id_medoc);

    @Query("{ 'id_pharmacie' : ?0 }")
    List<Sortie>findById_pharmacie(String id_pharmacie);
}
