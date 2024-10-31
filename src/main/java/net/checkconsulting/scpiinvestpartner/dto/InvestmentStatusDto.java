package net.checkconsulting.scpiinvestpartner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InvestmentStatusDto {

    private String investmentLabel;
    private String investmentStatus;
}
