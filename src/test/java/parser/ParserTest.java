package parser;

import customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parseParameterContents() {
        Parser.userInput = "add-user /u test /a 18 /c 92750174";
        String[] parameters = { "/u" , "/a" , "/c"};
        String[] contents = Parser.parseParameterContents(parameters);
        assert contents[0].equals("test");
        assert contents[1].equals("18");
        assert contents[2].equals("92750174");
    }

    @Test
    void parseIntoCustomer() {
        Parser.userInput = "add-user /u test /a 18 /c 92750174";
        Customer customer = Parser.parseIntoCustomer();
        assertEquals("test" , customer.getUsername());
        assertEquals(18 , customer.getAge());
        assertEquals( 92750174 , customer.getContactNumber());
    }
}
