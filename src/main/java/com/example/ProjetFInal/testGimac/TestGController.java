package com.example.ProjetFInal.testGimac;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;


@RestController
@RequestMapping("/pp")
public class TestGController {



    @CrossOrigin(origins = "*")
    @PostMapping("/oauth/token")
    private Object create(@RequestBody Object input){

        System.out.println(input);
        Object error = null;
        try {
            JSONObject json = new JSONObject(input.toString());

            String password = json.getString("password");
            System.out.println(password);
            if(password.compareTo("<password>")==0){
                System.out.println("--------------------------");
                 error = new GimacError("invalid_grant", "Bad credentials");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(error!=null){
            return error;
        }

        return new Token("42c93229-59b2-49fd-9366-69f5e7177712",
                "bearer","61ecd5fc-53bb-447e-bcb4-8d8873e59876",120,"read");
    }



    @CrossOrigin(origins = "*")
    @PostMapping("/payment/send")
    private GimacTransaction createTransaction(@RequestBody GimacTransaction transaction){

        System.out.println(transaction);
        String intent = transaction.getIntent();
        int rand=new Random().nextInt(100000);
        int rand2=new Random().nextInt(1000000);
        int rand3=new Random().nextInt(10000000);


        System.out.println(transaction.getIntent());
            transaction.setVouchercode("GIMAC"+ rand+rand2+rand3);
            transaction.setState("PENDING");
            this.manageThread(transaction);
        System.out.println("****************** before return *******************");

        return transaction ;
    }

    private void manageThread(GimacTransaction transaction) {



                ResponseThread myRunnable = new ResponseThread(transaction);

                Thread thread = new Thread(myRunnable);

                thread.start();

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/payment/update")
    private GimacTransaction updateTransaction(@RequestBody GimacTransaction transaction){

        System.out.println(transaction);
        String intent = transaction.getIntent();
        if(intent.equals("cardless_withdrawal")){
            System.out.println("cardless_withdrawal");
            //transaction.setVouchercode("GIMAC945215625942016");

            //transaction.setSendermobile("+212522564541");
            //transaction.setTomember("PerfectPay");
            transaction.setState("ACCEPTED");
            //transaction.setCreatetime(1490109044L);
            //transaction.setExpirytime(1490133333L);
           // if(!transaction.getState().equals("CANCELLED") || !transaction.getState().equals("REVERSED")  ) transaction.setIssuertrxref("KAKOTEL_28439016-2f89-4414-92b1-7c0b208e0fcc");

        }

        return transaction ;
    }
}

