package net.checkconsulting.scpiinvestpartner.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.checkconsulting.scpiinvestpartner.configuration.kafka.InvestmentMessage;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentRequestDto;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentStatusDto;
import net.checkconsulting.scpiinvestpartner.entity.Investment;
import net.checkconsulting.scpiinvestpartner.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static net.checkconsulting.scpiinvestpartner.enums.InvestStatus.*;

@Service
@Slf4j
public class InvestmentService {

    @Value("${spring.profiles.active}")
    public String env;

    private final InvestmentRepository investmentRepository;
    private final KafkaTemplate<String, InvestmentMessage> kafkaTemplate;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, KafkaTemplate<String, InvestmentMessage> kafkaTemplate) {
        this.investmentRepository = investmentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Investment createInvestment(InvestmentRequestDto dto) {
        Investment investment = new Investment();
        investment.setPropertyType(dto.getPropertyType());
        investment.setNumberOfShares(dto.getNumberOfShares());
        investment.setTotalAmount(dto.getTotalAmount());
        investment.setStripping(dto.getStripping());
        investment.setInvestmentLabel(dto.getInvestmentLabel());
        investment.setPartnerName(dto.getPartnerName());
        investment.setStatus(PENDING);
        investment.setDecisionDate(null);
        return investmentRepository.save(investment);
    }

    public void updateInvestmentStatus(InvestmentStatusDto investmentStatusDto) {


        investmentRepository.findInvestmentByInvestmentLabel(investmentStatusDto.getInvestmentLabel()).ifPresent(investment -> {
            if (investmentStatusDto.getInvestmentStatus() == REJECTED) {
                investment.setStatus(REJECTED);
            }
            else if (investmentStatusDto.getInvestmentStatus() == VALIDATED && investment.getStatus() == PAYMENT_RECEIVED) investment.setStatus(VALIDATED);
            investmentRepository.save(investment);
            InvestmentMessage investmentMessage = InvestmentMessage.builder()
                    .label(investment.getInvestmentLabel())
                    .status(investment.getStatus().toString())
                    .decisionDate(LocalDateTime.now())
                    .reason(investmentStatusDto.getReason())
                    .build();
            kafkaTemplate.send("investments-status-" + env, "", investmentMessage);
        });
    }

}