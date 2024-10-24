package transaction;

public class Transaction {
    private static int transactionCounter = 1;
    private final String transactionId;
    private final String carLicensePlate;
    private final String borrowerName;
    private final String duration;
    private final String startDate;



    public Transaction(String carLicensePlate, String borrowerName, String duration,
                       String startDate) {
        this.transactionId = "TX" + String.valueOf(transactionCounter++);
        this.carLicensePlate = carLicensePlate;
        this.borrowerName = borrowerName;
        this.duration = duration;
        this.startDate = startDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return transactionId + " | " + carLicensePlate + " | " + borrowerName + " | " +
                duration + "day(s) " + '\n' + "Start Date: " + startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }
}
