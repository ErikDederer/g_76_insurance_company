package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService service = new CustomerService();

    public Customer save(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    public List<Customer> getAllActiveCustomers() {
        return service.getAllActiveCustomers();
    }

    public Customer findById(String id) {
        Long numericId = Long.parseLong(id);
        return service.getActiveCustomerById(numericId);

    }

    public void update(String id, String newName) {
        Long numericId = Long.parseLong(id);
        service.update(numericId, newName);
    }

    public void updateRating(String id, String newRating) {
        Long numericId = Long.parseLong(id);
        double numericRating = Double.parseDouble(newRating);
        service.updateRating(numericId, numericRating);
    }

    public void deleteById(String id) {
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);
    }

    public void deleteCustomerByName(String name) {
        service.deleteByName(name);
    }

    public void restoreCustomerById(String id) {
        Long numericId = Long.parseLong(id);
        service.restoreById(numericId);

    }

    public int getActiveCustomersCount() {
        return service.getActiveCustomersNumber();
    }

    public double getCustomerTotalCoverage(String id) {
        Long numericId = Long.parseLong(id);
        return service.getCustomerTotalCoverage(numericId);
    }

    public double getCustomerAverageCoverage(String id) {
        Long numericId = Long.parseLong(id);
        return service.getCustomerAverageCoverage(numericId);
    }

    public void addPolicyToCustomer(String customerId, String policyId) {
        Long numericCustomerId = Long.parseLong(customerId);
        Long numericPolicyId = Long.parseLong(policyId);
        service.addPolicyToCustomer(numericCustomerId, numericPolicyId);
    }

    public void removePolicyFromCustomer(String customerId, String policyId) {
        Long numericCustomerId = Long.parseLong(customerId);
        Long numericPolicyId = Long.parseLong(policyId);
        service.removePolicyFromCustomerById(numericCustomerId, numericPolicyId);
    }

    public void clearCustomerPolicies(String customerId) {
        Long numericCustomerId = Long.parseLong(customerId);
        service.clearCustomerPolicies(numericCustomerId);
    }





}
