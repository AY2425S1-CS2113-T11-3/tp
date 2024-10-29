package car;

import exceptions.CarException;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Comparator;


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
                + " | $" + car.getPrice() + " | " + car.getRentedStatus() + " | " + car.getExpensiveStatus() + " | " + "Median price: " + getMedianPrice());
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
                    + " | $" +car.getPrice() + " | " + car.getRentedStatus() + " | " + car.getExpensiveStatus() + " | " + "Median price: " + getMedianPrice());
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

    public static void sortCarsByPrice() {
        Collections.sort(carsList, Comparator.comparingDouble(Car::getPrice));
    }

    public static double getMedianPrice() {
        if (carsList.isEmpty()) {
            return Integer.parseInt(null);
        }
        int middleIndex = carsList.size() / 2;
        if (carsList.size() % 2 == 0) {
            // For even-sized lists, choose the lower middle element
            middleIndex--;
        }
        return carsList.get(middleIndex).getPrice();
    }
    public static void markCarAsExpensive() {
        for (Car car : carsList) {
            if (car.getPrice() > getMedianPrice()) {
                car.markAsExpensive();
            } else {
                car.markAsCheap();
            }
        }
    }
}
