package com.example.redisstuey.sessionExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/")
    public String index(HttpSession session) {
        session.setAttribute("name", "sun");
        return session.getId() + "\nHello " + session.getAttribute("name");
    }
}
