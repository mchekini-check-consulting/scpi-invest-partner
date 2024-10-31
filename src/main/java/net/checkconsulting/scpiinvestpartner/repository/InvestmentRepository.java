package net.checkconsulting.scpiinvestpartner.repository;

import net.checkconsulting.scpiinvestpartner.entity.Investment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InvestmentRepository extends MongoRepository<Investment, String>{

    Optional<Investment> findInvestmentByInvestmentLabel(String investmentLabel);

}
