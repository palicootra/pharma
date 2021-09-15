package com.example.ProjetFInal.services;


import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Utilisateur;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur finduser = utilisateurRepository.findByUsername(username);
        if (finduser == null){return null;}
        String name = finduser.getUsername();
        String pwd = finduser.getPassword();
        return new User(name,pwd,new ArrayList<>());
    }


    public Utilisateur findUser(String name){
        return utilisateurRepository.findByUsername(name);
    }
    public void delete(Utilisateur utilisateur){

        utilisateurRepository.delete(utilisateur);
    }

    public Optional<Utilisateur> getById(String id) {
        return utilisateurRepository.findById(id);
    }
}
