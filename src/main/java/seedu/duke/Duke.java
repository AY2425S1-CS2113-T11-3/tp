package seedu.duke;

import customer.Customer;
import customer.CustomerList;
import exceptions.CustomerException;
import parser.Parser;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        printGreetings();
        getName();
        String action = Parser.getAction();

        try {
            switch (action) {
            case "add-user":
                Customer customer = Parser.parseIntoCustomer();
                CustomerList.addCustomer(customer);
                break;
            case "extra":
                System.out.println("extra case for switch");
                break;
            default:
                System.out.println("Invalid Command");
                break;
            }
        }catch(CustomerException exception){
            exception.printErrorMessage();

        }catch(NumberFormatException exception){
            System.out.println("Unable to parse customer");
        }

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
