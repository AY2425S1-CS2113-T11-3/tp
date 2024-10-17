package customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CustomerListTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testAddCustomer() {
        CustomerList.getCustomers().clear();
        Customer customer1 = new Customer("John" , 18 , 98414916);
        Customer customer2 = new Customer("Mary" , 20 , 98411416);
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        assertEquals(2 , CustomerList.getCustomers().size());
        assertEquals(customer1, CustomerList.getCustomers().get(0));
        assertEquals(customer2, CustomerList.getCustomers().get(1));

        CustomerList.getCustomers().remove(customer1);
        CustomerList.getCustomers().remove(customer2);
    }

    @Test
    public void testListCustomers() {
        CustomerList.getCustomers().clear();
        Customer customer1 = new Customer("John" , 18 , 98414916);
        Customer customer2 = new Customer("Mary" , 20 , 98411416);
        CustomerList.addCustomer(customer1);
        CustomerList.addCustomer(customer2);
        System.setOut(new PrintStream(outputStreamCaptor));
        CustomerList.printCustomers();
        String expectedOutput =
                "____________________________________________________________\n" +
                "Here are our customers: \n\n" +
                "Username : John\n" +
                "Age : 18\n" +
                "Contact Number : 98414916\n" +
                "____________________________________________________________\n" +
                "Username : Mary\n" +
                "Age : 20\n" +
                "Contact Number : 98411416\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());

    }

}
