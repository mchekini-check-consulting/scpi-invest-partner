package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.dto.PaymentDto;
import net.checkconsulting.scpiinvestpartner.entity.Payment;
import net.checkconsulting.scpiinvestpartner.repository.PayementRepository;
import org.springframework.stereotype.Service;

@Service
public class PayementService {
    private final PayementRepository payementRepository;

    public PayementService(PayementRepository payementRepository) {
        this.payementRepository = payementRepository;
    }

    public void savePayement(PaymentDto paymentDto) {
        Payment payment = Payment.builder()
                .bic(paymentDto.getBic())
                .iban(paymentDto.getIban())
                .label(paymentDto.getLabel())
                .build();

        payementRepository.save(payment);
    }
}
