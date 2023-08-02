package dev.arthurlunkes.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
