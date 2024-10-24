package car;

import exceptions.CarException;

import java.util.ArrayList;

public class CarList {

    public static ArrayList<Car> carsList = new ArrayList<>();

    public static ArrayList<Car> getCarsList() {
        return carsList;
    }

    public static void addCar(Car car) throws CarException {
        if (isExistingLicensePlateNumber(car.getLicensePlateNumber())) {
            throw CarException.duplicateLicensePlateNumber();
        }

        carsList.add(car);
        System.out.println("Car added to list");
        System.out.println("Car details:");
        System.out.println(car.getModel() + " | " + car.getLicensePlateNumber()
                + " | $" + car.getPrice() + " | " + car.getRentedStatus());
    }

    public static void removeCar(String carLicensePlateNumber) {
        Car carToRemove = null;

        // Iterate through the list of cars to find the one with the given license plate
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                carToRemove = car;
                break;
            }
        }

        // Remove the car if it exists
        if (carToRemove != null) {
            carsList.remove(carToRemove);
            System.out.println("Car with license plate " + carLicensePlateNumber + " removed from list.");
        } else {
            System.out.println("No car found with license plate " + carLicensePlateNumber);
        }
    }

    public static void printCarList(){
        System.out.println("Here are the current cars in the company");
        for(int i = 0 ; i < carsList.size(); i++){
            Car car = carsList.get(i);
            System.out.println( (i + 1) + ") " + car.getModel() + " | " + car.getLicensePlateNumber()
                    + " | $" +car.getPrice() + " | " + car.getRentedStatus());
        }
    }

    public static boolean isExistingLicensePlateNumber(String licensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(licensePlateNumber)) {
                return true;
            }
        }
        return false;
    }

    public static void markCarAsRented(String carLicensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                car.markAsRented();
                break;
            }
        }
    }

    public static void markCarAsAvailable(String carLicensePlateNumber) {
        for (Car car : carsList) {
            if (car.getLicensePlateNumber().equals(carLicensePlateNumber)) {
                car.markAsAvailable();
                break;
            }
        }
    }
}
