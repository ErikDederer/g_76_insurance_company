package app.service;

import app.domain.Claim;
import app.exceptions.ClaimNotFoundException;
import app.exceptions.ClaimSaveException;
import app.exceptions.CustomerUpdateException;
import app.repository.ClaimRepository;

import java.util.List;

public class ClaimService {

    public static ClaimService instance;

    private final ClaimRepository repository = new ClaimRepository();

    private ClaimService() {
    }

    public static ClaimService getInstance() {
        if (instance == null) {
            instance = new ClaimService();
        }
        return instance;
    }

    public Claim save(Claim claim) {
        if (claim == null) {
            throw new ClaimSaveException("Заявка не может быть null");
        }
        double coverage = claim.getCoverage();
        if (coverage < 0) {
            throw new ClaimSaveException("Сумма покрытия не может быть отрицательной");
        }
        if (claim.getType() == null) {
            throw new ClaimSaveException("Тип заявки не должен быть null");
        }
        claim.setActive(true);
        return repository.save(claim);
    }

    public List<Claim> getAllActiveClaims() {
        return repository.findAll()
                .stream()
                .filter(Claim::isActive)
                .toList();
    }

    public Claim getActiveClaimById(Long id) {
        Claim claim = repository.findById(id);
        if (claim == null || !claim.isActive()) {
            throw new ClaimNotFoundException(id);
        }
        return claim;
    }

    public void updateCoverage(Long id, double newCoverage) {
        Claim claim = repository.findById(id);
        if (claim == null || !claim.isActive()) {
            throw new ClaimNotFoundException(id);
        }
        if (newCoverage < 0) {
            throw new CustomerUpdateException("Сумма покрытия не может быть отрицательной");
        }
        repository.updateCoverage(id, newCoverage);
    }

    public void updateDecision(Long id, boolean newDecision) {
        Claim claim = repository.findById(id);
        if (claim == null || !claim.isActive()) {
            throw new ClaimNotFoundException(id);
        }
        repository.updateDecision(id, newDecision);
    }

    public void deleteById(Long id) {
        Claim claim = repository.findById(id);
        if (claim == null) {
            throw new ClaimNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void restoreById(Long id) {
        Claim claim = repository.findById(id);
        if (claim == null) {
            throw new ClaimNotFoundException(id);
        }
        claim.setActive(true);
    }

    public long getActiveClaimsCount() {
        return getAllActiveClaims().size();
    }

    public double getAllActiveClaimsTotalCoverage() {
        return getAllActiveClaims()
                .stream()
                .mapToDouble(Claim::getCoverage)
                .sum();
    }
    public double getActiveClaimsAverageCoverage() {
        return getAllActiveClaims()
                .stream()
                .mapToDouble(Claim::getCoverage)
                .average()
                .orElse(0.0);
    }
}