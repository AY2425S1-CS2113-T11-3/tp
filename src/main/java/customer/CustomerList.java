package customer;

import java.util.ArrayList;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
        System.out.println("Customer added");
        System.out.println(customer.toString());
    }

    public static void addCustomer(String username , int age , int contactNumber){
        Customer customer = new Customer(username, age, contactNumber);
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
}

