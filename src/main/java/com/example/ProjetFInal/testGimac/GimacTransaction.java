package com.example.ProjetFInal.testGimac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;

@Document( "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GimacTransaction {
        @Id
        private String id;
        private Long createtime;
        private Long expirytime;
        private String sendermobile;
        private String receivermobile;
        private String intent;
        private String walletdestination;
        @Indexed(unique=true,sparse=true)
        private String vouchercode;
        private String tomember;
        private String frommember;
        private String issuertrxref;
        private Float  amount;
        private String currency;
        private String description;
        private String state;
        private String error;
        private String error_description;
        private Long validityduration;



}
