package net.checkconsulting.scpiinvestpartner.service;

import net.checkconsulting.scpiinvestpartner.dto.InvestmentRequestDto;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentStatusDto;
import net.checkconsulting.scpiinvestpartner.entity.Investment;
import net.checkconsulting.scpiinvestpartner.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public HttpStatus updateInvestmentStatus(InvestmentStatusDto investmentStatusDto) {

        HttpStatus httpStatus;
        Optional<Investment> investment = investmentRepository.findInvestmentByInvestmentLabel(investmentStatusDto.getInvestmentLabel());

        if(investment.isPresent())
        {
            investment.get().setStatus(investmentStatusDto.getInvestmentStatus());
            investmentRepository.save(investment.get());
            httpStatus = HttpStatus.OK;
        }
        else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return httpStatus;
    }
}