package seedu.clirental;

import exceptions.CarException;
import exceptions.CliRentalException;
import exceptions.CustomerException;
import parser.Parser;

public class CliRental {
    /**
     * Main entry-point for the java.clirental.CliRental application.
     */
    public static void main(String[] args) {
        printGreetings();
        getName();

        boolean isExit = false;

        while (!isExit) {
            try {
                String action = Parser.getUserInput();

                if (Parser.parse(action)) {
                    isExit = true;
                }

                Parser.printDividerLine();

            } catch (CustomerException exception){
                exception.printErrorMessage();
            } catch (NumberFormatException exception){
                System.out.println("Unable to parse input");
            } catch (CarException | CliRentalException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    public static void getName(){
        System.out.println("What is your name?");
        System.out.println("Hello " + Parser.scanner.nextLine());
    }

    public static void printGreetings(){
        String logo =
                """
                        ____ _ _ ____            _        _
                        / ___| (_)  _ \\ ___ _ __ | |_ __ _| |
                        | |   | | | |_) / _ \\ '_ \\| __/ _` | |
                        | |___| | |  _ <  __/ | | | || (_| | |
                        \\____|_|_|_| \\_\\___|_| |_|\\__\\__,_|_|
                        """;
        System.out.println("Hello from\n" + logo);
    }
}
