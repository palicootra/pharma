package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    public  UtilisateurRepository utilisateurRepository;

    @CrossOrigin(origins = "*")
    @GetMapping ("/all")
    public List<Utilisateur> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createUser")
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur){
        try {
        Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
        return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("cet utilisateur existe déja", HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateUser")
    public ResponseEntity deleteCat(@RequestBody Utilisateur utilisateur){
      Utilisateur utilisateur1 = utilisateurRepository.findById(utilisateur.getId())
                .orElseThrow(()-> new RuntimeException(String.format("nous ne trouvons pas l'utilisateur pour id: %s",utilisateur.getId())));
        utilisateur1.setUsername(utilisateur.getUsername());
        utilisateur1.setFirstname(utilisateur.getFirstname());
        utilisateur1.setPassword(utilisateur.getPassword());
        utilisateur1.setEmail(utilisateur.getEmail());
        utilisateur1.setUserphone(utilisateur.getUserphone());
        utilisateur1.setSatutuser(utilisateur.getSatutuser());
        utilisateur1.setId_pharma(utilisateur.getId_pharma());
        utilisateur1.getAdresse().setPays(utilisateur.getAdresse().getPays());
        utilisateur1.getAdresse().setVille(utilisateur.getAdresse().getVille());
        utilisateur1.getAdresse().setRegion(utilisateur.getAdresse().getRegion());
        utilisateur1.getAdresse().setDepartement(utilisateur.getAdresse().getDepartement());
        utilisateur1.getAdresse().setLongitude(utilisateur.getAdresse().getLongitude());
        utilisateur1.getAdresse().setLatitude(utilisateur.getAdresse().getLatitude());
        utilisateur1.getAdresse().setCode_postal(utilisateur.getAdresse().getCode_postal());
        utilisateurRepository.save(utilisateur1);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping ("/deleteUser")
    public ResponseEntity<String> delete(@RequestParam String id_user){
        utilisateurRepository.deleteById(id_user);
        String supp = "supression effectué";
        return new ResponseEntity<>(supp, HttpStatus.OK);
    }


}
