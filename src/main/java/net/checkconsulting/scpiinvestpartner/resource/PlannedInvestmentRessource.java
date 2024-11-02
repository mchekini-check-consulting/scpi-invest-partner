package net.checkconsulting.scpiinvestpartner.resource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.checkconsulting.scpiinvestpartner.dto.InvestmentStatusDto;
import net.checkconsulting.scpiinvestpartner.dto.PlannedInvestmentDto;
import net.checkconsulting.scpiinvestpartner.repository.PlannedInvestmentRepository;
import net.checkconsulting.scpiinvestpartner.service.PlannedInvestmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/planned-invest")
public class PlannedInvestmentRessource {
    private final PlannedInvestmentService plannedInvestmentService;
    private final PlannedInvestmentRepository plannedInvestmentRepository;

    public PlannedInvestmentRessource(PlannedInvestmentService plannedInvestmentService, PlannedInvestmentRepository plannedInvestmentRepository) {
        this.plannedInvestmentService = plannedInvestmentService;
        this.plannedInvestmentRepository = plannedInvestmentRepository;
    }

    @PostMapping()
    @Operation(summary = "Create a planned investment",
            description = "Creates a new planned investment and returns its details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Planned investment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PlannedInvestmentDto> createPlannedInvestment(@RequestBody PlannedInvestmentDto investment) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/investments/" +  plannedInvestmentService.createPlannedInvestment(investment));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @PutMapping()
    public void updatePlannedInvestment(@RequestBody InvestmentStatusDto status) {
        plannedInvestmentService.updateInvestmentStatus(status);
    }

}
