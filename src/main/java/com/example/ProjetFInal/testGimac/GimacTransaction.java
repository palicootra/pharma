package com.example.ProjetFInal.testGimac;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document( "Transaction")
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



        public GimacTransaction merge(GimacTransaction transaction) {
                if(this.getAmount()==null ) this.setAmount(transaction.getAmount());
                if(this.getIssuertrxref()==null ) this.setIssuertrxref(transaction.getIssuertrxref());
                if(this.getAcquirertrxref()==null ) this.setAcquirertrxref(transaction.getAcquirertrxref());
                if(this.getAtm()==null ) this.setAtm(transaction.getAtm());
                if(this.getBank()==null ) this.setBank(transaction.getBank());
                if(this.getBillInquiryData()==null) this.setBillInquiryData(transaction.getBillInquiryData());
                if(this.getBillList()==null) this.setBillList(transaction.getBillList());
                if(this.getBillRef()==null) this.setBillRef(transaction.getBillRef());
                if(this.getContractRef()==null) this.setContractRef(transaction.getContractRef());
                if(this.getCreatetime()==null ) this.setCreatetime(transaction.getCreatetime());
                if(this.getCurrency()==null ) this.setCurrency(transaction.getCurrency());
                if(this.getDescription()==null ) this.setDescription(transaction.getDescription());
                if(this.getError()==null ) this.setError(transaction.getError());
                if(this.getError_description()==null ) this.setError_description(transaction.getError_description());
                if(this.getExpirytime()==null ) this.setExpirytime(transaction.getExpirytime());
                if(this.getFrommember()==null ) this.setFrommember(transaction.getFrommember());
                if(this.getIntent()==null ) this.setIntent(transaction.getIntent());
                if(this.getIssuertrxref()==null ) this.setIssuertrxref(transaction.getIssuertrxref());
                if(this.getReceivermobile()==null ) this.setReceivermobile(transaction.getReceivermobile());
                if(this.getSendercustomerdata()==null ) this.setSendercustomerdata(transaction.getSendercustomerdata());
                if(this.getReceivercustomerdata()==null ) this.setReceivercustomerdata(transaction.getReceivercustomerdata());


                if(this.getSendermobile()==null ) this.setSendermobile(transaction.getSendermobile());
                if(this.getService()==null ) this.setService(transaction.getService());

                if(this.getServiceRef()==null ) this.setServiceRef(transaction.getServiceRef());
                if(this.getState()==null ) this.setState(transaction.getState());
                if(this.getToMember()==null ) this.setToMember(transaction.getToMember());
                //always update updated time
                if(transaction.getUpdatetime()!=null ) this.setUpdatetime(transaction.getUpdatetime());
                if(this.getValidityduration()==null ) this.setValidityduration(transaction.getValidityduration());
                if(this.getVouchercode()==null ) this.setVouchercode(transaction.getVouchercode());
                if(this.getWalletdestination()==null ) this.setWalletdestination(transaction.getWalletdestination());
                if(this.getWalletsource()==null) this.setWalletsource(transaction.getWalletsource());


                return this;
        }


        public void cleanForGimac() {
                this.setResponse(null);
                this.setDirection(null);
                this.setId(null);
                this.setStates(null);
                this.setAction(null);
        }
        public boolean verifyIncomingTransaction(){
                if (this.toMember.equals(Constants.Strings.PERFECT_PAY)){
                        return false;
                }
                return true;
        }
}
