package net.checkconsulting.scpiinvestpartner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.checkconsulting.scpiinvestpartner.enums.InvestStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "investments")
public class Investment {
    @Id
    private String id;
    private String propertyType;
    private int numberOfShares;
    private double totalAmount;
    private Integer stripping;
    private String investmentLabel;
    private InvestStatus status;
    private String partnerName;
    private LocalDateTime decisionDate;
}
