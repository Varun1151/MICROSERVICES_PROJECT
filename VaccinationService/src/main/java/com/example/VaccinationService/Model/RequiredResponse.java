package com.example.VaccinationCenter.Model;

import java.util.List;

import com.example.VaccinationCenter.Entity.VaccinationCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RequiredResponse {
	private VaccinationCenter center;
	private List<Citizen> citizens;
}