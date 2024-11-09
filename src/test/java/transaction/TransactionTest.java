package transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.TransactionParser.dateTimeFormatter;

class TransactionTest {
    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    void setUp() {

        // Initialize two Transaction instances before each test
        LocalDate startDate1 = LocalDate.parse("01-10-2024", dateTimeFormatter);
        LocalDate startDate2 = LocalDate.parse("02-10-2024", dateTimeFormatter);
        transaction1 = new Transaction("ABC-123", "John Doe", 5, startDate1);
        transaction2 = new Transaction("XYZ-789", "Jane Smith", 3, startDate2);
    }

    @Test
    void testTransactionIdGeneration() {
        // Assuming the transactionCounter starts at 1 and increments by 1 for each new Transaction
        assertEquals("TX6", transaction1.getTransactionId());
        assertEquals("TX7", transaction2.getTransactionId());
    }

    @Test
    void testGetCarLicensePlate() {
        assertEquals("ABC-123", transaction1.getCarLicensePlate(), "Car license plate should match");
        assertEquals("XYZ-789", transaction2.getCarLicensePlate(), "Car license plate should match");
    }

    @Test
    void testGetCustomer() {
        assertEquals("John Doe", transaction1.getCustomer(), "Customer name should match");
        assertEquals("Jane Smith", transaction2.getCustomer(), "Customer name should match");
    }

    @Test
    void testIsCompletedInitiallyFalse() {
        assertFalse(transaction1.isCompleted(), "New transaction should not be completed");
        assertFalse(transaction2.isCompleted(), "New transaction should not be completed");
    }

    @Test
    void testSetCompleted() {
        transaction1.setCompleted(true);
        assertTrue(transaction1.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(true);
        assertTrue(transaction2.isCompleted(), "Transaction should be marked as completed");

        transaction2.setCompleted(false);
        assertFalse(transaction2.isCompleted(), "Transaction should be marked as not completed");
    }

    @Test
    void testToStringWhenNotCompleted() {
        String expected1 = "[ ] " + transaction1.getTransactionId() +
                " | ABC-123 | John Doe | 5 days" + System.lineSeparator() + "Start Date: 01-10-2024"
                + " | End Date: 06-10-2024";
        String expected2 = "[ ] " + transaction2.getTransactionId() +
                " | XYZ-789 | Jane Smith | 3 days" + System.lineSeparator() + "Start Date: 02-10-2024"
                + " | End Date: 05-10-2024";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when not completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when not completed");
    }

    @Test
    void testToStringWhenCompleted() {
        transaction1.setCompleted(true);
        transaction2.setCompleted(true);

        String expected1 = "[X] " + transaction1.getTransactionId() +
                " | ABC-123 | John Doe | 5 days" + System.lineSeparator() + "Start Date: 01-10-2024"
                + " | End Date: 06-10-2024";
        String expected2 = "[X] " + transaction2.getTransactionId() +
                " | XYZ-789 | Jane Smith | 3 days" + System.lineSeparator() + "Start Date: 02-10-2024"
                + " | End Date: 05-10-2024";

        assertEquals(expected1, transaction1.toString(), "toString should match expected format when completed");
        assertEquals(expected2, transaction2.toString(), "toString should match expected format when completed");
    }

    @Test
    void testMultipleTransactionIds() {
        // Create additional transactions to test transactionCounter increments correctly
        LocalDate startDate3 = LocalDate.parse("03-10-2024", dateTimeFormatter);
        LocalDate startDate4 = LocalDate.parse("04-10-2024", dateTimeFormatter);
        Transaction transaction3 = new Transaction("LMN-456", "Alice Johnson", 2, startDate3);
        Transaction transaction4 = new Transaction("DEF-321", "Bob Brown", 4, startDate4);

        assertEquals("TX16", transaction3.getTransactionId(), "Third transaction ID should be TX3");
        assertEquals("TX17", transaction4.getTransactionId(), "Fourth transaction ID should be TX4");
    }
}
