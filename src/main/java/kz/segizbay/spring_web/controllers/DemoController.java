package kz.segizbay.spring_web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/api/v1/demo")
    public String securedDemo(){
        return "secured";
    }
}
