package exceptions;

public class CustomerException extends RuntimeException {

    public static final String ADD_FORMAT = "add-user /u [USERNAME] /a [AGE] /c [CONTACT_NUMBER]";

    public CustomerException(String message) {
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(getMessage());
    }

    public static CustomerException addCustomerException(){
        return new CustomerException("Unable to add customer. Please follow : " + ADD_FORMAT);
    }
}
