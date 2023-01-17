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


  //  @Entity annotation is used to mark the class as a persistent Java class.

//@Table annotation is used to provide the details of the table that this entity will be mapped to.
//@Id annotation is used to define the primary key.
//@GeneratedValue annotation is used to define the primary key generation strategy. In the above case, we have declared the primary key to be an Auto Increment field.
//@Column annotation is used to define the properties of the column that will be mapped to the annotated field. You can define several properties like name, length, nullable, updateable, etc.