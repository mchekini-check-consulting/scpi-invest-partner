package net.checkconsulting.scpiinvestpartner.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannedInvestementDto {
    public String firstName;
    public String lastName;
    public String email;
    public double montant;
    public String frequence;
    public int jourPrelevement;
    public int nombreDePart;
    public String typeDePropriete;
}
