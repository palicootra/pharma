package com.example.ProjetFInal.testGimac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;
import java.util.List;

@Document( "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GimacTransaction {
        private Float  amount;
        @Indexed(unique=true,sparse=true)
        private String aquirertrxref;
        private String atm;
        private String bank;
        private Long   createtime;
        private String currency;
        private String description;
        private String error;
        private String error_description;
        private Long   expirytime;
        private String frommember;
        private String intent;
        @Indexed(unique=true,sparse=true)
        private String issuertrxref;
        private String receivermobile;
        private String receivercustomerdata;
        private String sendercustomerdata;
        private String sendermobile;
        private String state;
        private String tomember;
        private Long   updatetime;
        private Long   validityduration;
        @Indexed(unique=true,sparse=true)
        private String vouchercode;
        private String walletdestination;
        private String walletsource;
        private String direction;
        private String action;
        private List<String> states;


}
