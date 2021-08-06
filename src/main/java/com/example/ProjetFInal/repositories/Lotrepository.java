package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Lot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Lotrepository extends MongoRepository<Lot,String> {
}
