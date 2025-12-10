package app.repository;

import app.domain.Claim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClaimRepository {

    private final Map<Long, Claim> database = new HashMap<>();
    private long maxId;

    public Claim save(Claim claim) {
        claim.setId(++maxId);
        database.put(maxId, claim);
        return claim;
    }

    public Claim findById(Long id) {
        return database.get(id);
    }

    public List<Claim> findAll() {
        return new ArrayList<>(database.values());
    }

    public void updateDecision(Long id, boolean newDecision) {
        Claim claim = database.get(id);
        if (claim != null) {
            claim.setDecision(newDecision);
        }
    }

    public void updateCoverage(Long id, double newCoverage) {
        Claim claim = database.get(id);
        if (claim != null) {
            claim.setCoverage(newCoverage);
        }
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}
