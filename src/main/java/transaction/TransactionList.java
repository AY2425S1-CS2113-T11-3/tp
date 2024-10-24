package transaction;

import car.CarList;
import exceptions.CarException;

import java.util.ArrayList;

public class TransactionList {
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    public static final String ADD_TRANSACTION_FORMAT = "add-tx /p [CAR_LICENSE_PLATE]" +
            "/u [BORROWER_NAME] /d [DURATION] /s [START_DATE]";
    private static final ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void removeTransaction(String userInput) {

        String[] words = userInput.split(" ");
        if ( (words.length < 2) || (!words[1].equals("/t")) ) {
            throw new IllegalStateException("Unable to remove transaction. " +
                    "Please follow : " + REMOVE_TRANSACTION_FORMAT);
        }
        if(!removeTransactionById(words[3])) {
            throw new RuntimeException("Transaction ID doesn't exist");
        }
    }

    public static boolean removeTransactionById(String transactionId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId().equals(transactionId)) {
                transactionList.remove(transaction);
                return true;
            }
        }
        return false;
    }

    public static void addTx(Transaction transaction) {
        String licensePlateNumber = transaction.getCarLicensePlate();
        if (!CarList.isExistingLicensePlateNumber(licensePlateNumber)){
            throw CarException.licensePlateNumberNotFound();
        }
        transactionList.add(transaction);
        CarList.markCarAsRented(licensePlateNumber);
        System.out.println("Transaction added: ");
        System.out.println(transaction.toString());
    }
          
    public static void printAllTransactions() {
        int index = 1;

        if (transactionList.isEmpty()) {
            System.out.println("No transaction available.");
            return;
        }

        System.out.println("Here are all the transactions: ");

        for (Transaction transaction : transactionList) {
            System.out.println(index + ") " + transaction.getTransactionId()
                    + " | " + transaction.getCarLicensePlate()
                    + " | " + transaction.getBorrowerName()
                    + " | " + transaction.getDuration()
                    + " | " + transaction.getStartDate());
            index++;
        }
    }
}
