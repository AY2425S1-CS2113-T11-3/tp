package exceptions;

public class CarException extends RuntimeException{

    private static final String ADD_CAR_FORMAT = "add-car /n [CAR_MODEL] /c [CAR_ID] /p [PRICE]";
    private static final String LICENSE_PLATE_NUMBER_FORMAT = "\nLicense Plate number format: SXX####X"
            + "\nX -> Letters [A - Z], # -> Numbers [0 - 9]";

    public CarException(String message) {
        super(message);
    }

    public static CarException addCarException() {
        String message = "Unable to add car.. Refer to correct format below:\n" +
                ADD_CAR_FORMAT;
        return new CarException(message);
    }

    public static CarException invalidPrice() {
        String message = "Unable to add car.. Price cannot be negative!!";
        return new CarException(message);
    }

    public static CarException invalidLicensePlateNumber() {
        String message = "Unable to add car.. License Plate number is invalid."
                + LICENSE_PLATE_NUMBER_FORMAT;
        return new CarException(message);
    }

    public static CarException duplicateLicensePlateNumber() {
        String message = "Unable to add car.. License Plate number already exists!!";
        return new CarException(message);
    }

    public static CarException licensePlateNumberNotFound() {
        String message = "Car license plate number not found!!"
                + "\nUse command <list-cars> to view list of available cars.";
        return new CarException(message);
    }

}
