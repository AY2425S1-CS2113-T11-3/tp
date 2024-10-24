package parser;

import transaction.Transaction;

import java.time.LocalDate;

public class TransactionParser {

    private static final String ADD_TRANSACTION_COMMAND = "add-tx";

    public static Transaction parseIntoTransaction(String userInput) throws IllegalArgumentException {
        userInput = userInput.substring(ADD_TRANSACTION_COMMAND.length()).trim();
        String[] parameters = { "/p", "/u", "/d", "/s" };
        String[] parameterContents;

        if (isValidSequence(parameters, userInput)) {
            parameterContents = parseParameterContents(parameters, userInput);
        } else {
            throw new IllegalArgumentException("Invalid command format for adding a transaction.");
        }

        String carLicensePlate = parameterContents[0];
        String userName = parameterContents[1];
        int duration = Integer.parseInt(parameterContents[2]);
        LocalDate startDate = LocalDate.parse(parameterContents[3]);

        return new Transaction(carLicensePlate.toUpperCase(), userName, String.valueOf(duration),
                startDate.toString());
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

}
