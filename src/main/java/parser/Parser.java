package parser;

import car.Car;
import car.CarList;
import customer.Customer;
import customer.CustomerList;
import transaction.Transaction;
import exceptions.CliRentalException;
import transaction.TransactionList;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);
    private static final String HELP_COMMAND = "help";
    private static final String ADD_CUSTOMER_COMMAND = "add-user";
    private static final String REMOVE_CUSTOMER_COMMAND = "remove-user";
    private static final String LIST_USERS_COMMAND = "list-users";
    private static final String ADD_CAR_COMMAND = "add-car";
    private static final String REMOVE_CAR_COMMAND = "remove-car";
    private static final String ADD_TRANSACTION_COMMAND = "add-tx";
    private static final String LIST_CARS_COMMAND = "list-cars";
    private static final String REMOVE_TRANSACTION_COMMAND = "remove-tx";
    private static final String LIST_ALL_TRANSACTIONS = "list-tx";
    private static final String EXIT_COMMAND = "exit";

    public static void printDividerLine() {
        System.out.println("_".repeat(60));
    }

    public static String getUserInput(){
        System.out.println("What would you like to do?");
        printDividerLine();

        String userInput = scanner.nextLine().trim();

        return userInput;
    }

    public static boolean parse(String userInput) throws CliRentalException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case HELP_COMMAND:
            HelpParser.parseHelpCommand();
            return false;
        case ADD_CUSTOMER_COMMAND:
            Customer customer = CustomerParser.parseIntoCustomer(userInput);
            CustomerList.addCustomer(customer);
            return false;
        case ADD_CAR_COMMAND:
            Car car = CarParser.parseIntoCar(userInput);
            CarList.addCar(car);
            return false;
        case LIST_CARS_COMMAND:
            CarList.printCarList();
            return false;
        case ADD_TRANSACTION_COMMAND:
            try {
                Transaction transaction = TransactionParser.parseIntoTransaction(userInput);
                TransactionList.addTx(transaction);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_CAR_COMMAND:
            try {
                String carLicensePlateNumber = CarParser.parseCarLicenseForRemoval(userInput);
                CarList.removeCar(carLicensePlateNumber);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        case REMOVE_CUSTOMER_COMMAND:
            try {
                String username = CustomerParser.parseUsernameForRemoval(userInput);
                if (CustomerList.removeCustomer(username)) {
                    System.out.println("Customer " + username + " has been removed.");
                } else {
                    System.out.println("Customer " + username + " not found.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return false;
        case LIST_USERS_COMMAND:
            CustomerList.printCustomers();
            return false;
        case REMOVE_TRANSACTION_COMMAND:
            TransactionList.removeTransaction(userInput);
            return false;
        case LIST_ALL_TRANSACTIONS:
            TransactionList.printAllTransactions();
            return false;
        case EXIT_COMMAND:
            return true;
        default:
            throw CliRentalException.unknownCommand();
        }
    }
}
