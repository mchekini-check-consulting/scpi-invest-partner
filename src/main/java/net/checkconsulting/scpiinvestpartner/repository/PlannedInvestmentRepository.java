package net.checkconsulting.scpiinvestpartner.repository;

import net.checkconsulting.scpiinvestpartner.entity.PlannedInvestment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlannedInvestmentRepository extends MongoRepository<PlannedInvestment, String> {
    Optional<PlannedInvestment> findPlannedInvestmentByLabel(String investmentLabel);
}
