package parser;

import car.Car;
import exceptions.CarException;

public class CarParser {

    private static final String[] ADD_CAR_PARAMETERS = {"/n", "/c", "/p"};
    private static final int ADD_CAR_PARAMETERS_OFFSET = 2;
    private static final int LICENSE_PLATE_NUMBER_LENGTH = 8;

    public static Car parseIntoCar(String userInput) throws CarException {
        userInput = userInput.trim();

        if (!isValidFormat(userInput)) {
            throw CarException.addCarException();
        }

        String carModel = extractCarModel(userInput).trim();
        String carLicensePlateNumber = extractCarLicensePlateNumber(userInput).trim();
        String carPriceString = extractCarPrice(userInput).trim();
        double carPrice = Double.parseDouble(carPriceString);

        if (!isValidLicensePlateNumber(carLicensePlateNumber)) {
            throw CarException.invalidLicensePlateNumber();
        }

        if (!isValidPrice(carPrice)) {
            throw CarException.invalidPrice();
        }

        double formattedCarPrice = Double.parseDouble(String.format("%.2f", carPrice));

        return new Car(carModel, carLicensePlateNumber, formattedCarPrice);
    }

    private static String extractCarModel(String userInput) {
        int startIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[0]) + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfCarModel = userInput.indexOf(ADD_CAR_PARAMETERS[1]);

        String carModel = userInput.substring(startIndexOfCarModel, endIndexOfCarModel);
        if (carModel.trim().isEmpty()) {
            throw new CarException("Car model missing!!");
        }

        return carModel;
    }

    private static String extractCarLicensePlateNumber(String userInput) {
        int startIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[1])
                + ADD_CAR_PARAMETERS_OFFSET;
        int endIndexOfLicensePlateNumber = userInput.indexOf(ADD_CAR_PARAMETERS[2]);

        String carLicensePlateNumber = userInput.substring(startIndexOfLicensePlateNumber,
                endIndexOfLicensePlateNumber);
        if (carLicensePlateNumber.trim().isEmpty()) {
            throw new CarException("License plate number missing!!");
        }

        return carLicensePlateNumber.toUpperCase();
    }

    private static String extractCarPrice(String userInput) {
        int startIndexOfPrice = userInput.indexOf(ADD_CAR_PARAMETERS[2]) + ADD_CAR_PARAMETERS_OFFSET;

        String carPrice = userInput.substring(startIndexOfPrice);
        if (carPrice.trim().isEmpty()) {
            throw new CarException("Car price missing!!");
        }

        return carPrice;
    }

    public static boolean isValidFormat(String userInput) {
        for (String param : ADD_CAR_PARAMETERS) {
            if (!userInput.contains(param)) {
                return false;
            }
        }

        for (int i = 0; i < ADD_CAR_PARAMETERS.length - 1; i++) {
            if (userInput.indexOf(ADD_CAR_PARAMETERS[i]) > userInput.indexOf(ADD_CAR_PARAMETERS[i+1])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPrice(double price) {
        return !(price < 0.00);
    }

    public static boolean isValidLicensePlateNumber(String licensePlateNumber) {
        if (!licensePlateNumber.startsWith("S")) {
            return false;
        }

        if (licensePlateNumber.length() != LICENSE_PLATE_NUMBER_LENGTH) {
            return false;
        }

        char[] licensePlateNumberChars = licensePlateNumber.toCharArray();
        // Example: SGD1234X
        for (int i = 1; i < licensePlateNumber.length(); i++) {
            // Checks if second, third and last char are (uppercase) letters.
            if (i <= 2 || i > 6) {
                if (licensePlateNumberChars[i] < 'A' || licensePlateNumberChars[i] > 'Z') {
                    return false;
                }
            }
            // Checks if middle 4 chars are numbers.
            if (i >= 3 && i <= 6) {
                if (licensePlateNumberChars[i] < '0' || licensePlateNumberChars[i] > '9') {
                    return false;
                }
            }
        }

        return true;
    }

    public static String parseCarLicenseForRemoval(String userInput) throws CarException {
        userInput = userInput.trim();

        String licensePlateNumber = extractLicensePlateForRemoval(userInput).trim();
        if (licensePlateNumber.isEmpty()) {
            throw new CarException("License plate number missing!!");
        }

        return licensePlateNumber;
    }

    private static String extractLicensePlateForRemoval(String userInput) {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 2) {
            throw new CarException("Invalid format for removing a car. Use: remove-car /i [CAR_ID]");
        }
        return splitInput[2];  // Expecting the license plate number to be the second argument
    }
}
