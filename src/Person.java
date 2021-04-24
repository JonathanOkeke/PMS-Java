/**
 * The Person class describes the various people who be directly involved in a given project.
 *
 * Will include an Architect, Contractor and Customers as subclasses of this class.
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021
 */
public class Person {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    /**
     * Constructor method for the Person base class.
     * @param name The name of the given person (First name + Surname)
     * @param phoneNumber The phone number of the person which is of type String.
     * @param email The email address of the person of type String
     * @param address The physical address of the person of type String.
     */
    public Person(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    // Getters and Setters

    /**
     * Returns the name of the person object.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the phone number of the person object as a string.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates the phone number of the person object to the user defined one.
     * @param phoneNumber The new phone number for the person object.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email address of the person object.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email address of the person object.
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the physical address of the person object.
     * @return address
     */
    public String getAddress() {
        return address;
    }
}

