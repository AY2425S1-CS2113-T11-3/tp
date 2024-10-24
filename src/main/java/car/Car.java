package car;

import exceptions.CarException;

public class Car {

    private String model;
    private String licensePlateNumber;
    private double price;
    private boolean isRented;

    public Car(String model, String licensePlateNumber, double price) throws CarException{
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.price = price;
        isRented = false;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public double getPrice() {
        return price;
    }

    public void markAsRented() {
        isRented = true;
    }

    public void markAsAvailable() {
        isRented = false;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getRentedStatus(){
        if (isRented) {
            return "Rented";
        }
        return "Available";
    }
}
