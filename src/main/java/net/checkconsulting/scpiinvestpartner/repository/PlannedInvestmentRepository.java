package net.checkconsulting.scpiinvestpartner.repository;

import net.checkconsulting.scpiinvestpartner.entity.PlannedInvestment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlannedInvestmentRepository extends MongoRepository<PlannedInvestment, String> {
}
