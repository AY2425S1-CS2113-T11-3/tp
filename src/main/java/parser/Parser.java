package parser;

import customer.Customer;
import exceptions.CustomerException;
import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);
    public static String userInput;
    private static final String addCustomerCommand = "add-user";

    /**
     * Gets the first word of user input.
     */
    public static String getAction(){
        System.out.println("What would you like to do?");
        System.out.println("_".repeat(60));
        String userInput = scanner.nextLine().trim();
        String[] words = userInput.split(" ");
        Parser.userInput = userInput;
        return words[0];
    }

    /**
     * Creates new customer object based on user input
     *
     * @throws CustomerException if input is not compliant with format
     * @throws NumberFormatException if the age and contact content are not integer string
     */
    public static Customer parseIntoCustomer() throws CustomerException , NumberFormatException {
        userInput = userInput.substring(addCustomerCommand.length()).trim();
        String[] parameters = { "/u" , "/a" , "/c"};
        String[] parameterContents;
        if(isValidSequence(parameters)){
            parameterContents = parseParameterContents(parameters);
        }else{
            throw CustomerException.addCustomerException();
        }

        String username = parameterContents[0];
        int age = Integer.parseInt(parameterContents[1]);
        int contactNumber = Integer.parseInt(parameterContents[2]);
        return new Customer(username , age, contactNumber );
    }

    public static String[] parseParameterContents(String[] parameters){
        String[] contents = new String[parameters.length];
        for(int i = 0; i < parameters.length - 1; i++){
            int indexOfBeforeParameter = userInput.indexOf(parameters[i]);
            int indexOfAfterParameter = userInput.indexOf(parameters[i+1]);
            int endOfBeforeParameter = indexOfBeforeParameter + parameters[i].length();
            contents[i] = userInput.substring(endOfBeforeParameter, indexOfAfterParameter).trim();
        }
        int indexOfLastParameter = userInput.indexOf(parameters[parameters.length - 1]);
        int endOfLastParameter = indexOfLastParameter + parameters[parameters.length - 1].length();
        contents[parameters.length - 1] = userInput.substring(endOfLastParameter).trim();

        return contents;
    }

    private static boolean isValidSequence(String[] parameters){


        for (String parameter : parameters) {
            if (!userInput.contains(parameter)) {
                return false;
            }
        }

        for(int i = 1 ; i < parameters.length ; i++){
            if(userInput.indexOf(parameters[i]) < userInput.indexOf(parameters[i-1])){
                return false;
            }
        }

        return true;
    }
}
