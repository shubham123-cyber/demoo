package com.example.demoo.Repo;

import com.example.demoo.Model.Entitymodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sql extends JpaRepository<Entitymodel,Integer> {
}
