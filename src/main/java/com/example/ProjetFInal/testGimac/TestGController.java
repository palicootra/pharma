package com.example.ProjetFInal.testGimac;


import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/pp")
public class TestGController {



    @CrossOrigin(origins = "*")
    @PostMapping("/oauth/token")
    private Token create(@RequestBody Object input){
        System.out.println(input);


        return new Token("42c93229-59b2-49fd-9366-69f5e7177712",
                "bearer","61ecd5fc-53bb-447e-bcb4-8d8873e59876",300,"read");
    }
}

