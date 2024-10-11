package customer;

public class Customer {

    private String username;
    private int age;
    private int contactNumber;

    public Customer(String username , int age , int contactNumber){
        this.username = username;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

}
