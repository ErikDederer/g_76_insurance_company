package app.domain;

import java.util.Objects;

public class Policy {

    private Long id;
    private String number;          // Номер полиса
    private PolicyType type;        // Тип страхования
    private double coverageAmount;  // Страховая сумма
    private boolean active;         // Активен ли полис

    // Конструктор: клиент снаружи задаёт номер, тип и страховую сумму
    public Policy(String number, PolicyType type, double coverageAmount) {
        this.number = number;
        this.type = type;
        this.coverageAmount = coverageAmount;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public PolicyType getType() {
        return type;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(PolicyType type) {
        this.type = type;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(id, policy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                "Policy: id - %d, number - %s, type - %s, coverage - %.2f, active - %s",
                id, number, type, coverageAmount, active ? "yes" : "no"
        );
    }
}