package net.checkconsulting.scpiinvestpartner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentRequestDto {
    private String partnerName;
    private String propertyType;
    private int numberOfShares;
    private double totalAmount;
    private Integer stripping;
    private String investmentLabel;
}
