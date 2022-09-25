package com.example.spring.Service;


import com.example.spring.Entity.Contact;
import com.example.spring.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class contactService {

    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public void save(Contact contact){
        contactRepository.save(contact);
    }

    @Transactional
    public List<Contact> getContacts(){
        return contactRepository.getAll().stream().sorted(Comparator.comparing(i -> (int) i.getId())).collect(Collectors.toList());
    }

    @Transactional
    public void deleteContactById(long id){
        contactRepository.deleteById(id);
    }

    @Transactional
    public void changeContact(Contact updatedContact, Long id){
        Contact contact = contactRepository.findContactById(id);
        contact.setName(updatedContact.getName());
        contact.setNumber(updatedContact.getNumber());
        contact.setSurname(updatedContact.getSurname());
    }

    @Transactional
    public Contact getContactById(long id){
        return contactRepository.findContactById(id);
    }
}
