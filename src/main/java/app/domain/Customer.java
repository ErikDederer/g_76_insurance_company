package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Customer {

    private Long id;
    private String name;
    private boolean active;
    private double rating;                  // Рейтинг клиента
    private final List<Policy> policies = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }




    public List<Policy> getPolicies() {
        return policies;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // ===== equals / hashCode по id =====

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ===== toString =====

    @Override
    public String toString() {
        String info = String.format(
                "Customer: id - %d, name - %s, active - %s, rating - %.2f%n",
                id, name, active ? "yes" : "no", rating
        );
        StringBuilder builder = new StringBuilder(info);
        builder.append("Policies:");


        if (policies.isEmpty()) {
            builder.append(" (no policies)");
        } else {
            String policyList = policies.stream()
                    .map(Policy::getNumber)
                    .collect(Collectors.joining(", "));

            builder.append(" ").append(policyList);
        }

        return builder.toString();



    }
}