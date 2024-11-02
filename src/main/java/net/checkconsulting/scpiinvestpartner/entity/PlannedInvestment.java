package net.checkconsulting.scpiinvestpartner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private double montant;
    private String frequence;
    private int jourPrelevement;
    private int nombreDePart;
    private String typeDePropriete;
}

