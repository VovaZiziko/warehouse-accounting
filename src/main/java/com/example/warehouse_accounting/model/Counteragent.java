package com.example.warehouse_accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "counteragents")
@Data
@NoArgsConstructor
@ToString
public class Counteragent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String tin;//taxpayerIdentificationNumber;

    @Column(nullable = false)
    private String address;

    private String phone;
    private String email;
    @Column(nullable = false)
    private String bankAccount;
    @Column(nullable = false)
    private String bankName;
}
