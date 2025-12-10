package app.domain;

import java.util.Objects;

public class Claim {

    private Long id;
    private PolicyType type;
    private boolean decision;
    private boolean active;
    private double coverage;
    private Long customerId;

    public Claim(PolicyType type, Long customerId, double coverage) {
        this.type = type;
        this.customerId = customerId;
        this.coverage = coverage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PolicyType getType() {
        return type;
    }

    public void setType(PolicyType type) {
        this.type = type;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return Objects.equals(id, claim.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                "Claim: id - %d, customerId - %d, type - %s, coverage - %.2f, decision - %s, active - %s",
                id,
                customerId,
                type,
                coverage,
                decision ? "approved" : "rejected",
                active ? "yes" : "no"
        );
    }
}