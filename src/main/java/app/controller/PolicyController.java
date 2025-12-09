package app.controller;

import app.domain.Policy;
import app.domain.PolicyType;
import app.exceptions.PolicyTypeNotFoundException;
import app.service.PolicyService;

import java.util.List;

public class PolicyController {

    private final PolicyService service = PolicyService.getInstance();

    public Policy save(String number, String type, String coverageAmount) {
        double numericCoverage = Double.parseDouble(coverageAmount);

        PolicyType policyType;
        try {
            policyType = PolicyType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {

            throw new PolicyTypeNotFoundException(type);
        }

        Policy policy = new Policy(number, policyType, numericCoverage);
        return service.save(policy);
    }
    public List<Policy> getAllActivePolicies() {
        return service.getAllActivePolicies();
    }

    public Policy getPolicyById(String id) {
        Long numericId = Long.parseLong(id);
        return service.getActivePolicyById(numericId);

    }

    public void updateCoverageSum(String id, String newCoverageAmount) {
        Long numericId = Long.parseLong(id);
        double numericCoverageAmount = Double.parseDouble(newCoverageAmount);
        service.updateCoverage(numericId, numericCoverageAmount);
    }

    public void deleteById(String id) {
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);
    }

    public void deleteByNumber(String numner) {
        service.deleteByNumber(numner);

    }

    public void restoreById(String id) {
        Long numericId = Long.parseLong(id);
        service.restoreById(numericId);
    }

    public int getAllActivePoliciesCount() {
        return service.getActivePoliciesCount();

    }

    public double getAllActivePoliciesSum() {
        return service.getAllActivePoliciesTotalCoverage();
    }

    public double getAllActivePoliciesTotalCoverage() {
        return service.getAllActivePoliciesTotalCoverage();
    }

    public double getAllActivePoliciesAverageCoverage(){
        return service.getActivePolicyAverageCoverage();
    }

}
