package exceptions;

/**
 * Customer related exceptions
 */
public class CustomerException extends RuntimeException {

    public static final String ADD_FORMAT = "add-user /u [USERNAME] /a [AGE] /c [CONTACT_NUMBER]";

    public CustomerException(String message) {
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(getMessage());
    }

    /**
     * Exception for the add-user command
     */
    public static CustomerException addCustomerException(){
        return new CustomerException("Unable to add customer. Please follow : " + ADD_FORMAT);
    }
}
