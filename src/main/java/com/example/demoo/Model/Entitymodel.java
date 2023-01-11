package com.example.demoo.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Entitymodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idd;

    private String you;
}
