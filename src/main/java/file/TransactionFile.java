package file;

import exceptions.TransactionException;
import transcation.Transaction;
import transcation.TransactionList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file operation for the transaction.
 */
public class TransactionFile {

    private final String transactionDataFileName;
    private final String transactionDataFilePath ;
    private final File transactionDataFile;

    public TransactionFile(){
        this.transactionDataFileName = "transactionData.txt";
        this.transactionDataFilePath = FileHandler.getDirName() + "/" + transactionDataFileName;
        this.transactionDataFile = new File(transactionDataFilePath);
    }

    public TransactionFile(String filename){
        this.transactionDataFileName = filename;
        this.transactionDataFilePath = FileHandler.getDirName() + "/" + transactionDataFileName;
        this.transactionDataFile = new File(transactionDataFilePath);
    }

    public String getTransactionDataFilename() {
        return transactionDataFileName;
    }

    public void createTransactionFileIfNotExist(){
        if(!transactionDataFile.exists()){
            FileHandler.createNewFile(transactionDataFile);
        }
    }

    /**
     * Reads every line in the transactionData.txt file and add it to the current transaction list.
     *
     * @throws FileNotFoundException if transactionData.txt does not exist.
     * @throws TransactionException if there is corruption in file data.
     */
    public void loadTransactionData() throws FileNotFoundException, TransactionException {
        if(transactionDataFile.exists()){
            Scanner scanner = new Scanner(transactionDataFile);
            ArrayList<Integer> errorLines = new ArrayList<>();
            int line = 1;
            while (scanner.hasNext()) {
                scanLineAndAddTransaction(scanner, errorLines, line);
                line ++;
            }
            if(!errorLines.isEmpty()) {
                throw TransactionException.invalidParameters(errorLines);
            }
        }
    }

    /**
     * Loads data from transactionData.txt if the file exist.
     */
    public void loadTransactionDataIfExist(){
        try {
            loadTransactionData();
        } catch (FileNotFoundException e) {
            System.out.println("transactionData.txt not found in data directory. Please try again");
        } catch (TransactionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scans the current line and add data to current transaction list.
     *
     * @param errorLines List of line number which the data were wrongly formatted.
     * @param line the current line number.
     */
    public void scanLineAndAddTransaction(Scanner scanner, ArrayList<Integer> errorLines, int line) {
        String input = scanner.nextLine();
        String[] parameters = input.split(" \\| ");
        if(parameters.length != Transaction.NUMBER_OF_PARAMETERS){
            errorLines.add(line);
        }else{
            addTransactionWithParameters(parameters);
        }
    }

    /**
     * Add transaction object to the list according to the parameters
     *
     * @param parameters parameters of the Transaction object
     */
    public void addTransactionWithParameters(String[] parameters) {
        String carLicensePlate = parameters[0];
        String borrowerName = parameters[1];
        String duration = parameters[2];
        String startDate = parameters[3];
        Transaction transaction = new Transaction(carLicensePlate , borrowerName , duration , startDate);
        TransactionList.addTxWithoutPrintingInfo(transaction);
    }

    /**
     * Reads the current transaction list and updates transactionData.txt file.
     *
     * @throws IOException File does not exist.
     */
    public void updateTransactionDataFile() throws IOException {
        FileWriter fw = new FileWriter(transactionDataFile);
        String textToAdd = TransactionList.transactionListToFileString();
        fw.write(textToAdd);
        fw.close();
    }

    public String getAbsolutePath(){
        return transactionDataFile.getAbsolutePath();
    }
}