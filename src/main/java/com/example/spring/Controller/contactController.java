package com.example.spring.Controller;


import com.example.spring.Entity.Contact;
import com.example.spring.Repository.ContactRepository;
import com.example.spring.Service.contactService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class contactController {

    @Autowired
    contactService contactService;

    @PostMapping("/addContact")
    @ResponseBody
    public Contact saveContact(@RequestBody Contact contact){
        contactService.save(contact);
        return contact;
    }

    @PostMapping("/deleteContact/{id}")
    @ResponseBody
    public Long deleteContact(@PathVariable Long id){
        contactService.deleteContactById(id);
        return id;
    }

    @PostMapping("/updateContact/{id}")
    @ResponseBody
    public Contact updateContact(@RequestBody Contact contact, @PathVariable long id){
        contactService.changeContact(contact, id);
        return contactService.getContactById(id);
    }

    @PostMapping("/getContact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Long id){
        return contactService.getContactById(id);
    }
}
