package com.example.ProjetFInal.testGimac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;
import java.util.List;
import java.util.Random;

@Document( "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GimacTransaction {
        @Id
        private String id;
        private Float  amount;
        @Indexed(unique=true,sparse=true)
        private String acquirertrxref;
        private String atm;
        private String bank;
        private Map<String,String> billInquiryData;
        private List<Map<String,String>> billList;
        private String contractRef;
        private String billRef;
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
        private Map<String,Object> receivercustomerdata;
        private Map<String,Object> sendercustomerdata;
        private String sendermobile;
        private String serviceRef;
        private String service;

        private String state;
        private String toMember;
        private Long   updatetime;
        private Long   validityduration;
        @Indexed(unique=true,sparse=true)
        private String vouchercode;
        private String walletdestination;
        private String walletsource;
        private String direction;
        private String action;
        //differents states of the transaction in order
        private List <String> states;

        private GimacTransaction response;



        public void generateVoucherCode() {
                int rand=new Random().nextInt(100000);
                int rand2=new Random().nextInt(1000000);
                int rand3=new Random().nextInt(10000000);
                this.setVouchercode("GIMAC"+ rand+rand2+rand3);
        }
}
