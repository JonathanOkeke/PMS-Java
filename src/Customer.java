/**
 * Customer subclass of the Person base class.
 * Defines a person object will the role of an Customer.
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021.
 */
public class Customer extends Person {
  public final String role;

  // Customer subclass constructor.
  public Customer(String name, String phoneNumber, String email, String address) {
    super(name, phoneNumber, email, address);
    this.role = "Customer";
  }

  /**
   * The toString() method
   * Generate a detailed summary of a customer's information.
   * @return The String variable "customerDetails".
   */
  public String toString() {
    String customerDetails = "\nName: " + getName();
    customerDetails += "\nRole: " + role;
    customerDetails += "\nPhone Number: " + getPhoneNumber();
    customerDetails += "\nEmail Address: " + getEmail();
    customerDetails += "\nPhysical Address: " + getAddress();

    return customerDetails;
  }
}
