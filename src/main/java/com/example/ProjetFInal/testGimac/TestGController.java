package com.example.ProjetFInal.testGimac;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;





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
    @PostMapping("/cashdeposit")
    private Object cashdeposit(@RequestBody Object input){
        if(input!=null){
            System.out.println(input);
        }

        JSONObject json = new JSONObject();

        return new Token("42c93229-59b2-49fd-9366-69f5e7177712",
                "bearer","61ecd5fc-53bb-447e-bcb4-8d8873e59876",120,"read");
    }
}

