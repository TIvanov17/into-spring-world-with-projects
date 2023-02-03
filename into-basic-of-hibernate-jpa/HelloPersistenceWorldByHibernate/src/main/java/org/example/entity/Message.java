package org.example.entity;

//The objective of this example is to store messages in a database and retrieve them for
//        display. The application has a simple persistent class, Message:

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }
}
