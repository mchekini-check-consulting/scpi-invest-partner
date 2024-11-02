package net.checkconsulting.scpiinvestpartner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.checkconsulting.scpiinvestpartner.enums.InvestStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "plannedInvestments")
@Builder
public class PlannedInvestment {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private double amount;
    private String frequency;
    private int debitDayOfMonth;
    private int numberOfShares;
    private String propertyType;
    private String label;
    private InvestStatus status;
    private LocalDate decisionDate;
    private String reason;
}

