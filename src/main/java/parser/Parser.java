package parser;

import java.util.Scanner;

public class Parser {

    public static Scanner scanner = new Scanner(System.in);

    public static String getAction(){
        String userInput = scanner.nextLine().trim();
        String firstWord = userInput.split("")[0];

        return firstWord;
    }
}
