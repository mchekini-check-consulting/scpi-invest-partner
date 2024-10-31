package net.checkconsulting.scpiinvestpartner.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentRequestDto;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentStatusDto;
import net.checkconsulting.scpiinvestpartner.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/investments")
public class InvestmentResource {
    private final InvestmentService investmentService;

    @Autowired
    public InvestmentResource(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @Operation(summary = "Create a new investment", description = "Stores a new investment record in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Investment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createInvestment(@RequestBody InvestmentRequestDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/investments/" + investmentService.createInvestment(dto).getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Update investment status", description = "Updates the status of an existing investment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investment status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status data provided"),
            @ApiResponse(responseCode = "404", description = "Investment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity<?> updateInvestmentStatus(@RequestBody InvestmentStatusDto investmentStatusDto) {
        HttpStatus httpStatus = investmentService.updateInvestmentStatus(investmentStatusDto);
        return ResponseEntity.status(httpStatus).build();
    }

}
