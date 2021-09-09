package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Adresse;
import com.example.ProjetFInal.modeles.Lot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Lotrepository extends MongoRepository<Lot,String> {
    @Query("{ 'nul_lot' : ?0 }")
    Lot findByNum_lot(String num_lot);

    @Query("{ 'id_medicament' : ?0 }")
    List<Lot> findById_medicament(String id_medicament);

    @Query("{ 'id_medicament' : ?0 }")
    List<Lot> findAllById_medicament(String id_medicament);

    @Query("{ 'id_pharmacie' : ?0 }")
    List<Lot> findById_pharmacie(String id_pharmacie);
}
