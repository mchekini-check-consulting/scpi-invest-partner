package net.checkconsulting.scpiinvestpartner.repository;

import net.checkconsulting.scpiinvestpartner.entity.Investment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvestmentRepository extends MongoRepository<Investment, String>{
}
