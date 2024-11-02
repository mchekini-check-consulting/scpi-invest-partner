package net.checkconsulting.scpiinvestpartner.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannedInvestmentDto {
    private String firstName;
    private String lastName;
    private String email;
    private double amount;
    private String frequency;
    private int debitDayOfMonth;
    private int numberOfShares;
    private String propertyType;
    private String label;
}
