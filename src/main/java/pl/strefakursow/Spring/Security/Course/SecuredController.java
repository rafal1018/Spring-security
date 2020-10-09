package pl.strefakursow.Spring.Security.Course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuredController {

    @GetMapping(value = "/secured-basic")
    public String securedPage() {
        return "secured-basic";
    }

    @GetMapping(value = "/secured-form")
    public String formSecuredPage(){
        return"secured-form";
    }
    @GetMapping(value = "/secured-form/login")
    public String formLogin(){
        return "login";
    }

}
