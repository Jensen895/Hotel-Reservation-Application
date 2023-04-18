package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static Map<String, Customer> customers = new HashMap<>();

    private CustomerService(){
    }

    public static void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public static Customer getCustomer(String email){
        return customers.get(email);
    }

    public static Collection<Customer> getAllCustomers(){
        return customers.values();
    }
}
