package com.example.spring.Repository;


import com.example.spring.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public void deleteById(long id);

    public Contact findContactById(long id);

    @Query(value = "select * from jpa.contacts_info", nativeQuery = true)
    public List<Contact> getAll();
}
