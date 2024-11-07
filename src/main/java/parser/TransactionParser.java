package parser;

import car.CarList;
import exceptions.CarException;
import transaction.Transaction;
import transaction.TransactionList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static parser.Parser.ADD_TRANSACTION_COMMAND;

public class TransactionParser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String ADD_TRANSACTION_FORMAT = "add-tx /c [CAR_ID]" +
            "/u [CUSTOMER_NAME] /d [DURATION] /s [START_DATE dd-MM-yyyy]";
    public static final String FIND_TRANSACTION_BY_CUSTOMER_FORMAT = "find-tx-by-customer /u [CUSTOMER_NAME]";
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    public static final String MARK_TRANSACTION_FORMAT = "mark-tx /t [TRANSACTION_ID]";
    public static final String UNMARK_TRANSACTION_FORMAT = "unmark-tx /t [TRANSACTION_ID]";



    public static Transaction parseIntoTransaction(String userInput) throws IllegalArgumentException {
        userInput = userInput.substring(ADD_TRANSACTION_COMMAND.length()).trim();
        String[] parameters = { "/c", "/u", "/d", "/s" };
        String[] parameterContents;

        if (isValidSequence(parameters, userInput)) {
            parameterContents = parseParameterContents(parameters, userInput);
        } else {
            throw new IllegalArgumentException("Invalid command format for adding a transaction. " +
                    "Refer to the format below: " + System.lineSeparator() + ADD_TRANSACTION_FORMAT);
        }

        String carLicensePlate = parameterContents[0];
        if (!CarParser.isValidLicensePlateNumber(carLicensePlate)) {
            throw CarException.invalidLicensePlateNumber();
        }
        if (!CarList.isExistingLicensePlateNumber(carLicensePlate)) {
            throw CarException.licensePlateNumberNotFound();
        }


        String userName = parameterContents[1];

        int duration;
        try {
            duration = Integer.parseInt(parameterContents[2]);
            if (duration < 1 || duration > 365) {
                throw new IllegalArgumentException("Invalid duration. Duration must be between 1 and 365 days.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration format. Duration must be an integer.");
        }

        LocalDate startDate;
        String dateStr = parameterContents[3];
        try {
            dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for startDate. Date format: dd-MM-yyyy.");
        }
        try {
            startDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("The specified startDate does not exist in the calendar.");
        }

        return new Transaction(carLicensePlate.toUpperCase(), userName, duration,
                startDate);
    }

    private static boolean isValidSequence(String[] parameters, String userInput) {
        for (String parameter : parameters) {
            if (!userInput.contains(parameter)) {
                return false;
            }
        }
        return true;
    }

    private static String[] parseParameterContents(String[] parameters, String userInput) {
        String[] contents = new String[parameters.length];
        String[] words = userInput.split(" ");

        for (int i = 0; i < parameters.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(parameters[i]) && j + 1 < words.length) {
                    contents[i] = words[j + 1];
                    break;
                }
            }
        }
        return contents;
    }

    public static void parseFindTxsByCustomer(String userInput) {
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/u")) {
            System.out.println("Unable to search for transaction. Refer to correct format below:");
            System.out.println(FIND_TRANSACTION_BY_CUSTOMER_FORMAT);
            return;
        }
        TransactionList.findTxsByCustomer(words[2].toLowerCase());
    }

    public static void parseRemoveTx(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to remove transaction. Refer to correct format below:");
            System.out.println(REMOVE_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.removeTxByTxId(words[2].toLowerCase());
    }

    public static void parseMarkCompleted(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to mark transaction. Refer to correct format below:");
            System.out.println(MARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.markCompletedByTxId(words[2].toLowerCase());
    }

    public static void parseUnmarkCompleted(String userInput){
        String[] words = userInput.split(" ",3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to unmark transaction. Refer to correct format below:");
            System.out.println(UNMARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.unmarkCompletedByTxId(words[2].toLowerCase());
    }

}
