package com.example.ProjetFInal.repositories;

import com.example.ProjetFInal.modeles.Employe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeRepository extends MongoRepository<Employe,String> {
}
