package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Medicament;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicamentReposotory extends MongoRepository<Medicament,String> {
}
