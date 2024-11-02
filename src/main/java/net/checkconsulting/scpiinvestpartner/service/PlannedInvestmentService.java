package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.dto.PlannedInvestementDto;
import net.checkconsulting.scpiinvestpartner.entity.PlannedInvestment;
import net.checkconsulting.scpiinvestpartner.repository.PlannedInvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedInvestmentService {

    private final PlannedInvestmentRepository plannedInvestmentRepository;

    @Autowired
    public PlannedInvestmentService(PlannedInvestmentRepository plannedInvestmentRepository) {
        this.plannedInvestmentRepository = plannedInvestmentRepository;
    }

    public String createPlannedInvestment(PlannedInvestementDto investment) {
        PlannedInvestment plannedInvestment =  PlannedInvestment.builder()
                .email(investment.getEmail())
                .firstName(investment.getFirstName())
                .lastName(investment.getLastName())
                .frequence(investment.getFrequence())
                .jourPrelevement(investment.getJourPrelevement())
                .montant(investment.getMontant())
                .nombreDePart(investment.getNombreDePart())
                .typeDePropriete(investment.getTypeDePropriete())
                .build();
        return plannedInvestmentRepository.save(plannedInvestment).getId();
    }
}
