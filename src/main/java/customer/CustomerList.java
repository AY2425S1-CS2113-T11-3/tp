package customer;

import java.util.ArrayList;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static void addCustomer(String username , int age , int contactNumber){
        Customer customer = new Customer(username, age, contactNumber);
        customers.add(customer);
    }


}

