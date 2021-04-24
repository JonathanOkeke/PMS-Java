import java.util.*;

/**
 * Contractor subclass of the Person base class.
 * Defines a person object will the role of an Contractor.
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021
 */
public class Contractor extends Person {
  public final String role;

  // Contractor subclass constructor.
  public Contractor(String name, String phoneNumber, String email, String address) {
    super(name, phoneNumber, email, address);
    this.role = "Contractor";
  }

  /**
   * Updates the specified contractor's contact details
   * @return An arraylist of size two with the updated contact information. (phoneNumber, email)
   *         If only the phone number is changed, the return arraylist will be (phoneNumber,0)
   *         If only the email address is changed, the returned arraylist will be (0, email)
   *         This arraylist will be used by the updateProjectContractor() Project method.
   */
  public ArrayList<String> updateContractorDetails() {
    ArrayList<String> values = new ArrayList<String>();
    Scanner s = new Scanner(System.in);
    System.out.println("\nEnter 1 - To change phone number only.\nEnter 2 - To change email address only.\nEnter 3 - To change both their phone number and email address.");
    String editChoice = s.nextLine();
    switch (editChoice) {
      case "1":
        System.out.println("\nEnter their updated phone number below.");
        String newPhoneNumber = s.nextLine();
        this.setPhoneNumber(newPhoneNumber);
        values.add(newPhoneNumber);
        values.add("0");
        values.add(String.valueOf(this.getName()));
        break;

      case "2":
        System.out.println("\nEnter their updated email address below.");
        String newEmail = s.nextLine();
        this.setEmail(newEmail);
        values.add("0");
        values.add(newEmail);
        values.add(String.valueOf(this.getName()));
        break;

      case "3":
        System.out.println("Enter their updated phone number below.");
        String newPhoneNumber1 = s.nextLine();
        this.setPhoneNumber(newPhoneNumber1);
        System.out.println("\nEnter their updated email address below.");
        String newEmail1 = s.nextLine();
        this.setEmail(newEmail1);
        values.add(newPhoneNumber1);
        values.add(newEmail1);
        values.add(String.valueOf(this.getName()));
        break;
    }
    return values;
  }

  /**
   * Returns the String "contractorDetails" which details all of the contractor's information.
   * @return The String "contractorDetails".
   */
  public String toString() {
    String contractorDetails = "\nName: " + getName();
    contractorDetails += "\nRole: " + role;
    contractorDetails += "\nPhone Number: " + getPhoneNumber();
    contractorDetails += "\nEmail Address: " + getName();
    contractorDetails += "\nPhysical Address: " + getAddress();

    return contractorDetails;
  }
}