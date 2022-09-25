package com.example.spring.Controller;

import com.example.spring.Service.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {

    @Autowired
    contactService contactService;

    @GetMapping("/")
    public String getPage(Model model){
        model.addAttribute("contacts", contactService.getContacts());
        return "index";
    }
}
