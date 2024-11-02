package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.configuration.kafka.InvestmentMessage;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentStatusDto;
import net.checkconsulting.scpiinvestpartner.dto.PlannedInvestmentDto;
import net.checkconsulting.scpiinvestpartner.entity.PlannedInvestment;
import net.checkconsulting.scpiinvestpartner.repository.PlannedInvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static net.checkconsulting.scpiinvestpartner.enums.InvestStatus.*;

@Service
public class PlannedInvestmentService {
    @Value("${spring.profiles.active}")
    public String env;

    private final PlannedInvestmentRepository plannedInvestmentRepository;
    private final KafkaTemplate<String, InvestmentMessage> kafkaTemplate;

    @Autowired
    public PlannedInvestmentService(PlannedInvestmentRepository plannedInvestmentRepository, KafkaTemplate<String, InvestmentMessage> kafkaTemplate) {
        this.plannedInvestmentRepository = plannedInvestmentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createPlannedInvestment(PlannedInvestmentDto investment) {
        PlannedInvestment plannedInvestment = PlannedInvestment.builder()
                .email(investment.getEmail())
                .firstName(investment.getFirstName())
                .lastName(investment.getLastName())
                .frequency(investment.getFrequency())
                .debitDayOfMonth(investment.getDebitDayOfMonth())
                .amount(investment.getAmount())
                .numberOfShares(investment.getNumberOfShares())
                .propertyType(investment.getPropertyType())
                .label(investment.getLabel())
                .status(PENDING)
                .build();
        return plannedInvestmentRepository.save(plannedInvestment).getId();
    }

    public void updateInvestmentStatus(InvestmentStatusDto investmentStatus) {

        plannedInvestmentRepository.findPlannedInvestmentByLabel(investmentStatus.getInvestmentLabel()).ifPresent(plannedInvestment -> {
            if (investmentStatus.getInvestmentStatus() == REJECTED) {
                plannedInvestment.setStatus(REJECTED);
            } else if (investmentStatus.getInvestmentStatus() == VALIDATED && plannedInvestment.getStatus() == PAYMENT_RECEIVED)
                plannedInvestment.setStatus(VALIDATED);
            plannedInvestmentRepository.save(plannedInvestment);
            InvestmentMessage investmentMessage = InvestmentMessage.builder()
                    .label(plannedInvestment.getLabel())
                    .status(plannedInvestment.getStatus().toString())
                    .decisionDate(LocalDateTime.now())
                    .reason(investmentStatus.getReason())
                    .build();
            kafkaTemplate.send("planned-investments-status-" + env, "", investmentMessage);
        });
    }
}
