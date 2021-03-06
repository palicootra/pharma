package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends MongoRepository<Categories,String> {
    @Query("{ 'nom_cat' : ?0 }")
    Optional<Categories> findByNom_cat(String nom_cat);
}
