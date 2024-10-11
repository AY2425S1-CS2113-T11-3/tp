package seedu.duke;

import customer.Customer;
import customer.CustomerList;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        printGreetings();
        getName();
        Customer customer1 = new Customer("John" , 18 , 98414916);
        Customer customer2 = new Customer("Mary" , 20 , 98411416);
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
    }

    public static void getName(){
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }

    public static void printGreetings(){
        String logo =
                """
                        ____ _ _ ____            _        _
                        / ___| (_)  _ \\ ___ _ __ | |_ __ _| |
                        | |   | | | |_) / _ \\ '_ \\| __/ _` | |
                        | |___| | |  _ <  __/ | | | || (_| | |
                        \\____|_|_|_| \\_\\___|_| |_|\\__\\__,_|_|
                        """;
        System.out.println("Hello from\n" + logo);
    }
}
