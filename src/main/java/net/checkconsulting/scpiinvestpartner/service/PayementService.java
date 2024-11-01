package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.dto.PaymentDto;
import net.checkconsulting.scpiinvestpartner.entity.Investment;
import net.checkconsulting.scpiinvestpartner.entity.Payment;
import net.checkconsulting.scpiinvestpartner.repository.InvestmentRepository;
import net.checkconsulting.scpiinvestpartner.repository.PayementRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static net.checkconsulting.scpiinvestpartner.enums.InvestStatus.PAYMENT_RECEIVED;

@Service
public class PayementService {
    private final PayementRepository payementRepository;
    private final InvestmentRepository investmentRepository;

    public PayementService(PayementRepository payementRepository, InvestmentRepository investmentRepository) {
        this.payementRepository = payementRepository;
        this.investmentRepository = investmentRepository;
    }

    public void savePayement(PaymentDto paymentDto) {
        Payment payment = Payment.builder()
                .bic(paymentDto.getBic())
                .iban(paymentDto.getIban())
                .label(paymentDto.getLabel())
                .build();

        payementRepository.save(payment);
        Optional<Investment> optionalInvestment = investmentRepository.findInvestmentByInvestmentLabel(paymentDto.getLabel());
        optionalInvestment.ifPresent(investment -> {
            investment.setStatus(PAYMENT_RECEIVED);
            investmentRepository.save(investment);
        });
    }
}
