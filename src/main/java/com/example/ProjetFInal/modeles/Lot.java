package com.example.ProjetFInal.modeles;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collation = "Lot")
public class Lot {

    @Id
    private String id;

    @Field(value = "num_lot")
    private String num_lot;

    @Field(value = "Qte_lot")
    private Integer qte_lot;

    @Field(value = "Datein_lot")
    private Date datein_lot;

    @Field(value = "Dateperem_lot")
    private Date deteperem_lot;

    @Field(value = "Qtedepart_lot")
    private Integer qtedepart_lot;

    @Field(value = "Qtefab_lot")
    private Integer qtefab_lot;

    public Lot(String num_lot, Integer qte_lot, Date datein_lot, Date deteperem_lot, Integer qtedepart_lot, Integer qtefab_lot) {
        this.num_lot = num_lot;
        this.qte_lot = qte_lot;
        this.datein_lot = datein_lot;
        this.deteperem_lot = deteperem_lot;
        this.qtedepart_lot = qtedepart_lot;
        this.qtefab_lot = qtefab_lot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(String num_lot) {
        this.num_lot = num_lot;
    }

    public Integer getQte_lot() {
        return qte_lot;
    }

    public void setQte_lot(Integer qte_lot) {
        this.qte_lot = qte_lot;
    }

    public Date getDatein_lot() {
        return datein_lot;
    }

    public void setDatein_lot(Date datein_lot) {
        this.datein_lot = datein_lot;
    }

    public Date getDeteperem_lot() {
        return deteperem_lot;
    }

    public void setDeteperem_lot(Date deteperem_lot) {
        this.deteperem_lot = deteperem_lot;
    }

    public Integer getQtedepart_lot() {
        return qtedepart_lot;
    }

    public void setQtedepart_lot(Integer qtedepart_lot) {
        this.qtedepart_lot = qtedepart_lot;
    }

    public Integer getQtefab_lot() {
        return qtefab_lot;
    }

    public void setQtefab_lot(Integer qtefab_lot) {
        this.qtefab_lot = qtefab_lot;
    }
}
