package app.controller;

import app.domain.Claim;
import app.domain.PolicyType;
import app.exceptions.PolicyTypeNotFoundException;
import app.service.ClaimService;

import java.util.List;

public class ClaimController {

    private final ClaimService service = ClaimService.getInstance();

    public Claim save(String type, String customerId, String coverage) {
        PolicyType policyType;
        try {
            policyType = PolicyType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PolicyTypeNotFoundException(type);
        }
        Long numericCustomerId = Long.parseLong(customerId);
        double numericCoverage = Double.parseDouble(coverage);
        Claim claim = new Claim(policyType, numericCustomerId, numericCoverage);
        return service.save(claim);
    }

    public List<Claim> getAll() {
        return service.getAllActiveClaims();
    }

    public Claim getById(String id) {
        Long numericId = Long.parseLong(id);
        return service.getActiveClaimById(numericId);
    }

    public void update(String id, String newCoverage) {
        Long numericId = Long.parseLong(id);
        double numericCoverage = Double.parseDouble(newCoverage);
        service.updateCoverage(numericId, numericCoverage);
    }

    public void updateDecision(String id, String decision) {
        Long numericId = Long.parseLong(id);
        boolean booleanDecision = Boolean.parseBoolean(decision);
        service.updateDecision(numericId, booleanDecision);
    }

    public void deleteById(String id) {
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);


    }

    public void restoreById(String id) {
        Long numericId = Long.parseLong(id);
        service.restoreById(numericId);
    }
    public long getClaimsCount() {
        return service.getActiveClaimsCount();
    }

    public double getClaimsTotalCoverage() {
        return service.getAllActiveClaimsTotalCoverage();
    }

    public double getClaimAverageCoverage() {
        return service.getActiveClaimsAverageCoverage();
    }

}
