package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.dto.InvestmentRequestDto;
import net.checkconsulting.scpiinvestpartner.entity.Investment;
import net.checkconsulting.scpiinvestpartner.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Investment createInvestment(InvestmentRequestDto dto) {
        Investment investment = new Investment();
        investment.setPropertyType(dto.getPropertyType());
        investment.setNumberOfShares(dto.getNumberOfShares());
        investment.setTotalAmount(dto.getTotalAmount());
        investment.setStripping(dto.getStripping());
        investment.setInvestmentLabel(dto.getInvestmentLabel());
        investment.setPartnerName(dto.getPartnerName());
        investment.setStatus("PENDING");
        investment.setDecisionDate(null);
        return investmentRepository.save(investment);
    }
}