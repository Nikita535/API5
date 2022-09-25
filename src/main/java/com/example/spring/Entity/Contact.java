package com.example.spring.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "contacts_info")
@Getter
@Setter
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String number;

    private String name;

    private String surname;

    public Contact(String number, String name, String surname){
        this.number = number;
        this.name = name;
        this.surname = surname;
    }

    public Contact() {

    }
}
