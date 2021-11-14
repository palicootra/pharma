package com.example.ProjetFInal.testGimac;

import java.io.IOException;

import com.example.ProjetFInal.testGimac.config.Configuration;
import com.squareup.okhttp.*;

public class ResponseThread implements Runnable {


    private final GimacTransaction transaction;
    private boolean doStop = false;

    public ResponseThread(GimacTransaction transaction) {
        this.transaction=transaction;
    }

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return !this.doStop;
    }

    @Override
    public void run() {

            // keep doing what this thread should do.
            System.out.println("ResponseThread");

            try {
                Thread.sleep(10L * 1000L);
                this.sendToProxyGimac(this.transaction);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

    public GimacTransaction sendToProxyGimac(GimacTransaction transaction){

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String stringBody = this.buildBody(transaction);

        System.out.println(stringBody);
        RequestBody body = RequestBody.create(mediaType, stringBody);
        Request request = new Request.Builder()
                .url(Configuration.serverBaseUrl+Configuration.updateLink)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                //.addHeader("Authorization", "Bearer "+token.getAccess_token())
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());

            if (response.code()==200 || response.code()==400){
                String responseData = response.body().string();
                System.out.println(responseData);

                //GimacTransaction transaction1 = gson.fromJson(responseData, GimacTransaction.class);
                System.out.println(transaction.getVouchercode()+ " sent");

            }

            else return null;
        } catch (IOException | IllegalStateException e) {

            e.printStackTrace();
        }
        return null;
    }

    private String buildBody(GimacTransaction transaction) {
        String stringBody="{";
        if(transaction.getAmount()!=null){
            stringBody=stringBody+ "\"amount\":"+transaction.getAmount()+",";
        }
        if(transaction.getAquirertrxref()!=null){
            stringBody=stringBody+ "\"acquirerrxref\":\""+transaction.getAquirertrxref()+"\",";
        }
        if(transaction.getAtm()!=null){
            stringBody=stringBody+ "\"atm\":\""+transaction.getAtm()+"\",";
        }
        if(transaction.getBank()!=null){
            stringBody=stringBody+ "\"bank\":\""+transaction.getBank()+"\",";
        }
        if(transaction.getCreatetime()!=null){
            stringBody=stringBody+ "\"createtime\":"+transaction.getCreatetime()+",";
        }
        if(transaction.getCurrency()!=null){
            stringBody=stringBody+ "\"currency\":\""+transaction.getCurrency()+"\",";
        }
        if(transaction.getDescription()!=null){
            stringBody=stringBody+ "\"description\":\""+transaction.getDescription()+"\",";
        }
        if(transaction.getError()!=null){
            stringBody=stringBody+ "\"error\":\""+transaction.getError()+"\",";
        }
        if(transaction.getError_description()!=null){
            stringBody=stringBody+ "\"error_description\":\""+transaction.getError_description()+"\",";
        }
        if(transaction.getExpirytime()!=null){
            stringBody=stringBody+ "\"expirytime\":\""+transaction.getExpirytime()+"\",";
        }
        if(transaction.getFrommember()!=null){
            stringBody=stringBody+ "\"frommember\":\""+transaction.getFrommember()+"\",";
        }

        if(transaction.getIntent()!=null){
            stringBody=stringBody+ "\"intent\":\""+transaction.getIntent()+"\",";
        }
        if(transaction.getIssuertrxref()!=null){
            stringBody=stringBody+ "\"issuertrxref\":\""+transaction.getIssuertrxref()+"\",";
        }
        if(transaction.getReceivermobile()!=null){
            stringBody=stringBody+ "\"receivermobile\":\""+transaction.getReceivermobile()+"\",";
        }
        if(transaction.getReceivercustomerdata()!=null){
            stringBody=stringBody+ "\"receivercustomerdata\":\""+transaction.getReceivercustomerdata()+"\",";
        }
        if(transaction.getSendercustomerdata()!=null){
            stringBody=stringBody+ "\"sendercustomerdata\":\""+transaction.getSendercustomerdata()+"\",";
        }

        if(transaction.getSendermobile()!=null){
            stringBody=stringBody+ "\"sendermobile\":\""+transaction.getSendermobile()+"\",";
        }
        if(transaction.getTomember()!=null){
            stringBody=stringBody+ "\"tomember\":\""+transaction.getTomember()+"\",";
        }
        if(transaction.getUpdatetime()!=null){
            stringBody=stringBody+ "\"updatetime\":\""+transaction.getUpdatetime()+"\",";
        }
        if(transaction.getValidityduration()!=null){
            stringBody=stringBody+ "\"validityduration\":"+transaction.getValidityduration()+",";
        }
        if(transaction.getVouchercode()!=null){
            stringBody=stringBody+ "\"vouchercode\":\""+transaction.getVouchercode()+"\",";
        }
        if(transaction.getWalletdestination()!=null){
            stringBody=stringBody+ "\"walletdestination\":\""+transaction.getWalletdestination()+"\",";
        }
        if(transaction.getWalletdestination()!=null){
            stringBody=stringBody+ "\"walletsource\":\""+transaction.getWalletsource()+"\",";
        }
        if(transaction.getState()!=null){
            stringBody=stringBody+ "\"state\":\""+transaction.getState()+"\",";
        }

        stringBody=stringBody+"}";
        stringBody=stringBody.replace(",}","}");
        return stringBody;
    }
}