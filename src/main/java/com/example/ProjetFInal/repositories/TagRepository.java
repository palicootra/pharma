package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Categories;
import com.example.ProjetFInal.modeles.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag,String> {

    @Query("{ 'libele' : ?0 }")
    Tag findByLibele (String libele);

}
