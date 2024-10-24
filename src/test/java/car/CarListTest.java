package car;

import exceptions.CarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarListTest {

    @Test
    public void addCar_validCarObject() {
        Car car = new Car("Toyota Camry", "SLK1222D", 180);
        Car car1 = new Car("Nissan Qashqai", "SKA8898X", 250);

        CarList.addCar(car);
        CarList.addCar(car1);

        assertEquals(2, CarList.getCarsList().size());
        assertEquals(car, CarList.getCarsList().get(0));
        assertEquals(car1, CarList.getCarsList().get(1));

        CarList.getCarsList().remove(car);
        CarList.getCarsList().remove(car1);
    }

    @Test
    public void addCar_invalidCarObject_carExceptionThrown() throws CarException {
        assertEquals(0, CarList.getCarsList().size());
    }

    @Test
    public void testCarList() {
        CarList.addCar(new Car("Honda Civic" , "SGE1234T" , 123));
        CarList.addCar(new Car("Hyundai" , "SFR1224T" , 133));
        CarList.printCarList();
    }
}
