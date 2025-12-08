package app.service;

import app.domain.Customer;
import app.domain.Policy;
import app.exceptions.PolicyNotFoundException;
import app.repository.CustomerRepository;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;

import java.util.List;

public class CustomerService {

    private final CustomerRepository repository = new CustomerRepository();
    private final PolicyService policyService = PolicyService.getInstance();

    //Сохранить клиента в базе данных (при сохранении клиент автоматически считается активным).
    public Customer save(Customer customer) {
        if (customer == null) {
            throw new CustomerSaveException("Невозможно сохранить null клиента");
        }
        String name = customer.getName();
        if (name == null || name.isBlank()) {
            throw new CustomerSaveException("Имя клиента не может быть пустым");
        }
        customer.setActive(true);
        return repository.save(customer);
    }

    //Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAllActiveCustomers() {
        return repository.findAll().stream()
                .filter(Customer::isActive)
                .toList();
    }

    //Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getActiveCustomerById(Long id) {
        Customer customer = repository.findById(id);
        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    //Изменить одного клиента в базе данных по его идентификатору.

    public void update(Long id, String newName) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        if (newName == null || newName.isBlank()) {
            throw new CustomerSaveException("Имя клиента не может быть пустым");
        }
        repository.update(id, newName);
    }

    //Удалить клиента из базы данных по его идентификатору.

    public void deleteById(Long id) {
        Customer customer = getActiveCustomerById(id);
        customer.setActive(false);
    }

    //Удалить клиента из базы данных по его имени.
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }

    //Восстановить удалённого клиента в базе данных по его идентификатору.
    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        customer.setActive(true);
    }

    //Вернуть общее количество клиентов в базе данных (активных).
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

    public double getCustomerTotalCoverage(Long customerId) {
        Customer customer = getActiveCustomerById(customerId);
        return customer.getPolicies().stream()
                .filter(Policy::isActive)
                .mapToDouble(Policy::getCoverageAmount)
                .sum();
    }

    public double getCustomerAverageCoverage (Long customerId){
        Customer customer = getActiveCustomerById(customerId);
        return customer.getPolicies().stream()
                .filter(Policy::isActive)
                .mapToDouble(Policy::getCoverageAmount)
                .average()
                .orElse(0.0);
    }

    public void addPolicyToCustomer(Long customerId, Long policyId){
        Customer customer = getActiveCustomerById(customerId);
        Policy policy = policyService.getActivePolicyById(policyId);
        if (policy == null || !policy.isActive()){
            throw new PolicyNotFoundException(policyId);
        }
        customer.getPolicies().add(policy);
    }

    public void removePolicyFromCustomerId (Long customerId, Long policyId){
        Customer customer = getActiveCustomerById(customerId);
        customer.getPolicies().removeIf(p -> p.getId() != null && p.getId().equals(policyId));

    }

    public void clearCustomerPolicies(Long customerId){
        Customer customer = getActiveCustomerById(customerId);
        customer.getPolicies().clear();
    }




}
