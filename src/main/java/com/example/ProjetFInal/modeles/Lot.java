package com.example.ProjetFInal.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;


@Document("lot")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lot {

    @Id
    private String id;

    private String num_lot;
    private Integer qte_lot;
    private Date datein_lot;
    private Date deteperem_lot;
    private Integer qtedepart_lot;
    private Integer qtefab_lot;
    private Integer prix_lot;
    private String id_medicament;
    private String id_pharmacie;
    private String id_utilisateur;
    private boolean archived;
    private Date created_at;
}
