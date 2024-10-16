package car;

import exceptions.CarException;

public class Car {

    private String model;
    private String licensePlateNumber;
    private double price;

    public Car(String model, String licensePlateNumber, double price) throws CarException{
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        if (price < 0.00) {
            throw CarException.invalidPrice();
        } else {
            this.price = Double.parseDouble(String.format("%.2f", price));
        }

    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
