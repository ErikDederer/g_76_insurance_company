package app.service;

import app.domain.Policy;
import app.exceptions.PolicyNotFoundException;
import app.exceptions.PolicySaveException;
import app.exceptions.PolicyUpdateException;
import app.repository.PolicyRepository;

import java.util.List;

public class PolicyService {


    private static PolicyService instance;
    private final PolicyRepository repository = new PolicyRepository();

    private PolicyService() {
    }


    public static PolicyService getInstance() {
        if (instance == null) {
            instance = new PolicyService();
        }
        return instance;
    }

    public Policy save(Policy policy) {
        if (policy == null) {
            throw new PolicySaveException("Полис не может быть null");
        }
        if (policy.getCoverageAmount() < 0) {
            throw new PolicySaveException("Страховая сумма не может быть отрицательной");
        }
        String number = policy.getNumber();
        if (number == null || number.trim().isEmpty()) {
            throw new PolicySaveException("Номер полиса не должен быть пустым");
        }
        if (policy.getType() == null) {
            throw new PolicySaveException("Тип полиса не должен быть null");

        }
        policy.setActive(true);
        return repository.save(policy);
    }

    public List<Policy> getAllActivePolicies() {
        return repository.findAll()
                .stream()
                .filter(Policy::isActive)
                .toList();
    }

    public Policy getActivePolicyById(Long id) {
        Policy policy = repository.findById(id);
        if (policy == null || !policy.isActive()) {
            throw new PolicyNotFoundException(id);
        }
        return policy;
    }


    public void updateCoverage(Long id, double newCoverageAmount) {
        if (newCoverageAmount < 0) {
            throw new PolicyUpdateException("Новая страховая сумма не должна быть отрицательной");
        }
        repository.updateCoverageAmount(id, newCoverageAmount);
    }

    public void deleteById(Long id) {
        Policy policy = getActivePolicyById(id);
        policy.setActive(false);
    }

    public void deleteByNumber(String number) {
        for (Policy policy : repository.findAll() ) {
            if (policy.getNumber().equals(number)) {
                policy.setActive(false);
                break;
            }
        }
    }

    public void restoreById(Long id) {
        Policy policy = repository.findById(id);
        if (policy == null) {
            throw new PolicyNotFoundException(id);
        }
        policy.setActive(true);
    }

    public int getActivePoliciesCount() {
        return getAllActivePolicies().size();
    }

    public double getAllActivePoliciesTotalCoverage() {
        return getAllActivePolicies()
                .stream()
                .mapToDouble(Policy::getCoverageAmount)
                .sum();
    }


    public double getActivePolicyAverageCoverage() {
        return getAllActivePolicies()
                .stream()
                .mapToDouble(Policy::getCoverageAmount)
                .average()
                .orElse(0.0);

    }


}


