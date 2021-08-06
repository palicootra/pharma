package com.example.ProjetFInal.modeles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "Sorties")
public class Sortie {

    @Id
    private String id;

    @Field
    private String date_sort;

    @Field
    private Integer qte_sort;

    public Sortie(String date_sort, Integer qte_sort) {
        this.date_sort = date_sort;
        this.qte_sort = qte_sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_sort() {
        return date_sort;
    }

    public void setDate_sort(String date_sort) {
        this.date_sort = date_sort;
    }

    public Integer getQte_sort() {
        return qte_sort;
    }

    public void setQte_sort(Integer qte_sort) {
        this.qte_sort = qte_sort;
    }
}
