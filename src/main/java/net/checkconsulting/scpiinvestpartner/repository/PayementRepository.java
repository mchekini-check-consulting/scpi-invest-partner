package net.checkconsulting.scpiinvestpartner.repository;

import net.checkconsulting.scpiinvestpartner.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PayementRepository extends MongoRepository<Payment,Integer> {
}
