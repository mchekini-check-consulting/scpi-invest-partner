package net.checkconsulting.scpiinvestpartner.resource;

import net.checkconsulting.scpiinvestpartner.dto.PaymentDto;
import net.checkconsulting.scpiinvestpartner.service.PayementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentResource {

    private final PayementService payementService;

    public PaymentResource(PayementService payementService) {
        this.payementService = payementService;
    }

    @PostMapping
    public ResponseEntity<?> cratePayement(@RequestBody PaymentDto paymentDto) {
        payementService.savePayement(paymentDto);
        return ResponseEntity.ok().build();
    }
}
