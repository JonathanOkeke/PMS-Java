/**
 * Architect subclass of the Person base class.
 * Defines a person object will the role of an Architect.
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021
 */
public class Architect extends Person {
  final public String role;

  // Constructor method for the Architect class
  public Architect(String name, String phoneNumber, String email, String address) {

    super(name, phoneNumber, email, address);
    this.role = "Architect";
  }

  /**
   * Returns the String "architectDetails" which details all of the architect's information.
   * @return The String "architectDetails".
   */
  public String toString() {
    String architectDetails = "\nName: " + getName();
    architectDetails += "\nRole: " + role;
    architectDetails += "\nPhone Number: " + getPhoneNumber();
    architectDetails += "\nEmail Address: " + getEmail();
    architectDetails += "\nPhysical Address: " + getAddress();

    return architectDetails;
  }
}