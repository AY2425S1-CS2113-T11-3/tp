package car;

import exceptions.CarException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarTest {

    @Test
    public void testCarGetters_validParams() {
        Car car = new Car("Toyota 86", "SJX1234D", 120);
        assertEquals("Toyota 86", car.getModel());
        assertEquals("SJX1234D", car.getLicensePlateNumber());
        assertEquals(120, car.getPrice());
        assertFalse(car.isRented());
        assertEquals("Available" , car.getRentedStatus());

        Car car1 = new Car("ABC", "DE3F", 0);
        assertEquals("ABC", car1.getModel());
        assertEquals("DE3F", car1.getLicensePlateNumber());
        assertEquals(0, car1.getPrice());
        assertFalse(car1.isRented());
        assertEquals("Available" , car1.getRentedStatus());
    }

    @Test
    public void testCarGetters_negativePrice_carExceptionThrown() throws CarException {
        Car car = new Car("XYZ", "123", -0);
        assertEquals(0, car.getPrice());
    }

    // New test cases

    @Test
    public void testCarGetters_specialCharactersInModel_validCar() throws CarException {
        // Test for special characters in the car model name
        Car car = new Car("BMW-M3", "SJX1234D", 250);
        assertEquals("BMW-M3", car.getModel());
        assertEquals("SJX1234D", car.getLicensePlateNumber());
        assertEquals(250, car.getPrice());
    }

    @Test
    public void testCarGetters_veryLargePrice_validCar() throws CarException {
        // Test for very large prices
        Car car = new Car("Ferrari", "SJX1234D", 1000000);
        assertEquals("Ferrari", car.getModel());
        assertEquals("SJX1234D", car.getLicensePlateNumber());
        assertEquals(1000000, car.getPrice());
    }
    
}
