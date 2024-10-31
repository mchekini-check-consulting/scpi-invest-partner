package net.checkconsulting.scpiinvestpartner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private String status = "PENDING";
    private String partnerName;
    private Date decisionDate = null;
}
