package parser;

import car.Car;
import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarParserTest {

    @Test
    public void isValidFormat_validUserInput_expectTrue() {
        String userInput = "add-car /n civic /c 12345 /p 150";
        String userInput1 = "add-car /n Abc /c DEF123X /p 57.30";

        assertEquals(true, CarParser.isValidFormat(userInput));
        assertEquals(true, CarParser.isValidFormat(userInput1));
    }

    @Test
    public void isValidFormat_invalidUserInput_expectFalse() throws CarException, NumberFormatException {
        String userInput = "add-car civic ABC123 150";
        String userInput1 = "add-car /c BCE123 /n civic /p 150";
        String userInput2 = "add-car /p 130 /c XYZ888 /n civic";

        assertEquals(false, CarParser.isValidFormat(userInput));
        assertEquals(false, CarParser.isValidFormat(userInput1));
        assertEquals(false, CarParser.isValidFormat(userInput2));
    }

    @Test
    public void parseIntoCar_validUserInput_carObjectCreated() {
        String userInput = "add-car /n civic /c SGT1234X /p 150";

        Car car = CarParser.parseIntoCar(userInput);
        assertEquals("civic", car.getModel());
        assertEquals("SGT1234X", car.getLicensePlateNumber());
        assertEquals(150, car.getPrice());
    }

    @Test
    public void parseIntoCar_invalidUserInput_carExceptionThrown() throws CarException {
        String userInput = "add-car /n civic /c JKL12345 /p -138";
        String userInput1 = "add-car /n civic ABC123 /p 150";

        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput));
        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput1));
    }

    // Additional Test Cases

    @Test
    public void isValidFormat_missingRequiredFields_expectFalse() {
        String userInput = "add-car /n civic /p 150"; // Missing license plate number
        String userInput1 = "add-car /c 12345 /p 150"; // Missing car name

        assertEquals(false, CarParser.isValidFormat(userInput));
        assertEquals(false, CarParser.isValidFormat(userInput1));
    }

    @Test
    public void isValidFormat_boundaryPriceValue_expectTrue() {
        String userInput = "add-car /n civic /c 12345 /p 0"; // Boundary price value (0)
        String userInput1 = "add-car /n civic /c 12345 /p 99999"; // Upper boundary value for price

        assertEquals(true, CarParser.isValidFormat(userInput));
        assertEquals(true, CarParser.isValidFormat(userInput1));
    }

    @Test
    public void parseIntoCar_boundaryPriceValue_carObjectCreated() {
        String userInput = "add-car /n civic /c SCT6677K /p 0"; // Minimum price
        String userInput1 = "add-car /n civic /c SPL9773R /p 99999"; // Maximum valid price

        Car car = CarParser.parseIntoCar(userInput);
        assertEquals(0, car.getPrice());

        Car car1 = CarParser.parseIntoCar(userInput1);
        assertEquals(99999, car1.getPrice());
    }

    @Test
    public void parseIntoCar_invalidPrice_expectCarException() {
        String userInput = "add-car /n civic /c 12345 /p -100"; // Negative price
        String userInput1 = "add-car /n civic /c 12345 /p abc"; // Non-numeric price

        assertThrows(CarException.class, () -> CarParser.parseIntoCar(userInput));
        assertThrows(NumberFormatException.class, () -> CarParser.parseIntoCar(userInput1));
    }
}
