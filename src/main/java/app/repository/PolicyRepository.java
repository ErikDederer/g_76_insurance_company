package app.repository;

import app.domain.Policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolicyRepository {

    private final Map<Long, Policy> database = new HashMap<>();
    private long maxId;

    public Policy save(Policy policy) {
        policy.setId(++maxId);
        database.put(maxId, policy);
        return policy;
    }

    public Policy findById(Long id) {
        return database.get(id);
    }

    public List<Policy> findAll() {
        return new ArrayList<>(database.values());
    }

    public void updateCoverageAmount(Long id, double newCoverageAmount) {
        Policy policy = database.get(id);
        if (policy != null) {
            policy.setCoverageAmount(newCoverageAmount);

        }

    }

    public void deleteById(Long id) {
        database.remove(id);
    }

}
