package com.example.VaccinationCenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VaccinationCenter.Entity.VaccinationCenter;

@Repository
public interface VaccinationRepo extends JpaRepository<VaccinationCenter, Integer>{

}