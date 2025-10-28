package com.openapi.nasa.mvcController;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// Login Page Controller //
@Controller
@Hidden
public class LoginController {

    // Custom Login Page //
    @GetMapping("/show-login-page")
    public String customLogin()
    {
        return "nasa/custom-login-2";
    }

    // To Avoid favicon exceptions //
    @GetMapping("favicon.ico")
    @ResponseBody
    public void disableFavicon() {
        //Method is void to avoid browser 404 issue by returning nothing in the response.
    }
}
