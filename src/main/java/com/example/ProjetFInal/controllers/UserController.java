package com.example.ProjetFInal.controllers;


import com.example.ProjetFInal.enumeration.ROLES;
import com.example.ProjetFInal.modeles.Medicament;
import com.example.ProjetFInal.modeles.Sortie;
import com.example.ProjetFInal.services.MedicamentService;
import com.example.ProjetFInal.services.SortieService;
import com.example.ProjetFInal.utility.JwtResponse;
import com.example.ProjetFInal.modeles.Pharmacie;
import com.example.ProjetFInal.utility.Result;
import com.example.ProjetFInal.modeles.Utilisateur;
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
public class UserController {
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PharmacieService pharmacieService;
    private final MedicamentService medicamentService;
    private final SortieService sortieService;

    @Autowired
    public UtilisateurRepository utilisateurRepository;

    public UserController(JWTUtility jwtUtility, PharmacieService pharmacieService,
                          MedicamentService medicamentService,
                          AuthenticationManager authenticationManager, UserService userService, SortieService sortieService) {

            this.jwtUtility = jwtUtility;
            this.authenticationManager = authenticationManager;
            this.userService = userService;
            this.pharmacieService = pharmacieService;
            this.medicamentService=medicamentService;

        this.sortieService = sortieService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping ("/all")
    public ResponseEntity<Object> getAllUsers(@RequestParam (required = false) String id_pharmacie){
        List<Utilisateur> users;
        if(id_pharmacie!=null){
            users = userService.findById_pharmacie(id_pharmacie);
        }else{
            users = userService.findAll();
        }

        HashSet<Pharmacie> pharmacies = new HashSet<>();
        for (Utilisateur user :users) {
            if( user.getId_pharma() != null && user.getId_pharma().trim().length() > 0 ){
                System.out.println(user.getId_pharma());
                if(!this.pharmacieService.getPharma(user.getId_pharma()).isEmpty())
                pharmacies.add(this.pharmacieService.getPharma(user.getId_pharma()).get(0)) ;
            }

        }
        ArrayList<Object> response = new ArrayList<>();
        response.add(users);
        response.add(pharmacies);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur){
       try {
           utilisateur.setEtatuser(1);
           utilisateur.setSatutuser(true);
           ArrayList<String> role = new ArrayList<>();
           role.add(ROLES.USER.name());
           utilisateur.setRoles(role);
           utilisateur.setCreated_at(new Date());
           Utilisateur insertuser =  utilisateurRepository.insert(utilisateur);
           return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
       }catch (Exception e){
           e.printStackTrace();
           Result response = new Result("cet utilisateur existe déja", 409);

           return new ResponseEntity<>(response, HttpStatus.CONFLICT);
       }

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createUser")
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur){
        try {
            utilisateur.setEtatuser(1);
            utilisateur.setSatutuser(true);
            utilisateur.setCreated_at(new Date());
            System.out.println(utilisateur.toString());
            Utilisateur insertuser =  utilisateurRepository.save(utilisateur);
            return new ResponseEntity<>(insertuser, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            Result response = new Result("cet utilisateur existe déja", 409);

            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

    }

    @CrossOrigin(origins = "*")
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
    @DeleteMapping ("/delete")
    public ResponseEntity delete(@RequestBody Utilisateur  utilisateur){
        try {
            System.out.println(utilisateur.toString());
            userService.delete(utilisateur);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Optional<Utilisateur> user = this.userService.getById(utilisateur.getId());
        Result resultat;
        if(!user.isPresent()){
            System.out.println("+++++++++++++++++++++++++++++++");

            resultat =new Result("supression effectué",202);

        }else{
            resultat =new Result("Echec de supression",404);
        }


        return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
    }


    @CrossOrigin(origins = "*")
    @GetMapping ("/getstats")
    public ResponseEntity getstats(@RequestParam (required = false) String id_pharmacie){
        HashMap<String, Object> statistiques = new HashMap<>();
        long totalMedicaments;
        long totalSorties;
        long totalUtilisateurs;
        long totalPharmacies;

        if(id_pharmacie!= null){
            List<Medicament> medicaments= this.medicamentService.getByPharmacie(id_pharmacie);
            totalMedicaments=medicaments.size();

            List<Sortie> sorties= this.sortieService.findById_pharmacie(id_pharmacie) ;
            totalSorties=sorties.size();

            List<Utilisateur> utilisateurs= this.userService.findById_pharmacie(id_pharmacie);
            totalUtilisateurs=utilisateurs.size();



        }else{
            totalMedicaments = medicamentService.getNumber();
            totalSorties = this.sortieService.count();
            totalUtilisateurs = this.userService.count();
        }
        totalPharmacies=this.pharmacieService.count();


        statistiques.put("medicaments",totalMedicaments);
        statistiques.put("ventes",totalSorties);
        statistiques.put("utilisateurs",totalUtilisateurs);
        statistiques.put("pharmacies",totalPharmacies);





        return new ResponseEntity<>(statistiques, HttpStatus.OK);
    }



}
