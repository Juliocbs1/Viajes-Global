package com.viajesglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ViajesglobalApplication {

    /*echo "# Viajes-Global" >> README.md
    git init
    git add README.md
    git commit -m "first commit"
    git branch -M main
    git remote add origin https://github.com/Juliocbs1/Viajes-Global.git
    git push -u origin main
    */

    @GetMapping
    public String welcome() {
        return "Welcome to Viajesglobal----OMG";
    }

    public static void main(String[] args) {
        SpringApplication.run(ViajesglobalApplication.class, args);
    }

}
