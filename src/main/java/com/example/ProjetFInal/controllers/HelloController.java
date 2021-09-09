package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.modeles.*;
import com.example.ProjetFInal.repositories.UtilisateurRepository;
import com.example.ProjetFInal.services.PharmacieService;
import com.example.ProjetFInal.services.UserService;
import com.example.ProjetFInal.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HelloController {
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PharmacieService pharmacieService;

    @Autowired
    public UtilisateurRepository utilisateurRepository;

    public HelloController(JWTUtility jwtUtility,PharmacieService pharmacieService,
                           AuthenticationManager authenticationManager, UserService userService) {

            this.jwtUtility = jwtUtility;
            this.authenticationManager = authenticationManager;
            this.userService = userService;
            this.pharmacieService = pharmacieService;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping ("/all")
    public ResponseEntity<Object> getAllUsers(){
        List<Utilisateur> users = utilisateurRepository.findAll();
        HashSet<Pharmacie> pharmacies = new HashSet<>();
        for (Utilisateur user :users) {
            if( user.getId_pharma() == null || user.getId_pharma().trim().length() == 0 ){
                System.out.println(user.getId_pharma());

                pharmacies.add(this.pharmacieService.getPharma(user.getId_pharma()).get()) ;
            }

        }
        ArrayList<Object> response = new ArrayList<>();
        response.add(users);
        response.add(pharmacies);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createUser")
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur){
       try {
           utilisateur.setEtatuser(1);
           utilisateur.setSatutuser(true);
           utilisateur.setCreated_at(new Date());


           Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
           return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
       }catch (Exception e){
           e.printStackTrace();
           Result response = new Result("cet utilisateur existe déja", 409);

           return new ResponseEntity<>(response, HttpStatus.CONFLICT);
       }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public  ResponseEntity authenticate(@RequestBody Utilisateur utilisateur) throws BadCredentialsException {
        try {
            System.out.println(utilisateur.getUsername());
            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getUsername(),
                            utilisateur.getPassword()
                    )

            );
        }catch (BadCredentialsException e){
            e.printStackTrace();
            return new ResponseEntity<>(new Result("bad credentials",401), HttpStatus.UNAUTHORIZED);
           // return new JwtResponse(null,null);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(utilisateur.getUsername());
        final String token =
                jwtUtility.generateToken(userDetails);
        Utilisateur logU = userService.findUser(utilisateur.getUsername());
        return new ResponseEntity<>(new JwtResponse(token,logU), HttpStatus.OK);

        //return new ResponseEntity<>(token, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
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
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping ("/deleteUser")
    public ResponseEntity<String> delete(@RequestParam String id_user){
        utilisateurRepository.deleteById(id_user);
        String supp = "supression effectué";
        return new ResponseEntity<>(supp, HttpStatus.OK);
    }




}
