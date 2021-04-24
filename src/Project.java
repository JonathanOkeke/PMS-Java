import java.time.format.*;
import java.util.*;
import java.time.*;

/**
 * The Project class is the class that will be used to instantiate the various projects as objects will certain methods and variables that describe the projects.
 *
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021
 */

public class Project {
  private int projectNumber, erfNumber;
  private String projectName, buildingType, address, deadline, completionStatus, dateCompleted;
  private String architectName, architectNumber, architectEmail, architectAddress;
  private String customerName, customerNumber, customerEmail, customerAddress;
  private String contractorName, contractorNumber, contractorEmail, contractorAddress;
  private double totalProjectFee, amountPaid;

  /**
   * Constructor for the Project class
   * @param newProjectDetails An arraylist that will hold of the information (of type 'Object')  that describes the project.
   */
  public Project(ArrayList<Object> newProjectDetails, ArrayList<ArrayList<Object>> archList, ArrayList<ArrayList<Object>> conList, ArrayList<ArrayList<Object>> cuList) {
    this.projectNumber = Integer.parseInt(String.valueOf(newProjectDetails.get(0)));
    this.projectName = (String) newProjectDetails.get(1);
    this.erfNumber = Integer.parseInt(String.valueOf(newProjectDetails.get(2)));
    this.buildingType = (String) newProjectDetails.get(3);
    this.address = (String) newProjectDetails.get(4);
    this.totalProjectFee = Double.parseDouble(String.valueOf(newProjectDetails.get(5)));
    this.amountPaid = Double.parseDouble(String.valueOf(newProjectDetails.get(6)));
    this.deadline = String.valueOf(newProjectDetails.get(7));
    this.completionStatus = (String) newProjectDetails.get(8);
    this.dateCompleted = String.valueOf(newProjectDetails.get(9));
    this.architectName = (String) newProjectDetails.get(10);
    this.contractorName = (String) newProjectDetails.get(11);
    this.customerName = (String) newProjectDetails.get(12);
    for(ArrayList<Object> list1 : archList) {
      if (String.valueOf(list1.get(0)).equalsIgnoreCase((String) newProjectDetails.get(10))) {
        this.architectNumber = String.valueOf(list1.get(1));
        this.architectEmail = String.valueOf(list1.get(2));
        this.architectAddress = String.valueOf(list1.get(3));
        break;
      }
    }
    for(ArrayList<Object> list2 : conList) {
      if (String.valueOf(list2.get(0)).equalsIgnoreCase((String) newProjectDetails.get(11))) {
        this.contractorNumber = String.valueOf(list2.get(1));
        this.contractorEmail = String.valueOf(list2.get(2));
        this.contractorAddress = String.valueOf(list2.get(3));
        break;
      }
    }
    for(ArrayList<Object> list3 : cuList) {
      if (String.valueOf(list3.get(0)).equalsIgnoreCase((String) newProjectDetails.get(12))) {
        this.customerNumber = String.valueOf(list3.get(1));
        this.customerEmail = String.valueOf(list3.get(2));
        this.customerAddress = String.valueOf(list3.get(3));
        break;
      }
    }
  }
  // Getters and Setters for the class variables

  /**
   * Returns the specified project's project number.
   * @return projectNumber
   */
  public int getProjectNumber() {
    return projectNumber;
  }

  /**
   * Returns the project ERF number.
   * @return
   */
  public int getErfNumber() {
    return erfNumber;
  }

  /**
   * Returns the project's project name.
   * @return projectName
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * Returns the project's building type.
   * @return buildingType
   */
  public String getBuildingType() {
    return buildingType;
  }

  /**
   * Returns the project's physical address.
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the project's deadline.
   * @return deadline
   */
  public String getDeadline() {
    return deadline;
  }

  /**
   * Updates a project's deadline to a new date.
   * @param deadline  A new date specified by the user.
   */
  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }

  /**
   * Returns a project's completion status.
   * @return completionStatus. (Incomplete or Finalised).
   */
  public String getCompletionStatus() {
    return completionStatus;
  }

  /**
   * Updates the completion status of an incomplete project.
   * @param completionStatus The String "Finalised".
   */
  public void setCompletionStatus(String completionStatus) {
    this.completionStatus = completionStatus;
  }

  /**
   * Returns the date a project was completed.
   * @return dateCompleted
   */
  public String getDateCompleted() {
    return dateCompleted;
  }

  /**
   * Updates the a recently completed project with the date of completion.
   * @param dateCompleted The date which the project was completed.
   */
  public void setDateCompleted(String dateCompleted) {
    this.dateCompleted = dateCompleted;
  }

  /**
   * Returns the project's Architect's name.
   * @return architectName
   */
  public String getArchitectName() {
    return architectName;
  }

  /**
   * Returns the project's Architect's number.
   * @return architectNumber.
   */
  public String getArchitectNumber() {
    return architectNumber;
  }

  /**
   *Returns the project's Architect's email.
   * @return architectEmail
   */
  public String getArchitectEmail() {
    return architectEmail;
  }

  /**
   * Returns the project's Architect's physical address.
   * @return architectAddress
   */
  public String getArchitectAddress() {
    return architectAddress;
  }

  /**
   * Returns the project's Customer's name.
   * @return customerName
   */
  public String getCustomerName() {
    return customerName;
  }

  /**
   * Returns the project's Customer's number.
   * @return customerNumber
   */
  public String getCustomerNumber() {
    return customerNumber;
  }

  /**
   * Returns the project's Customer's email.
   * @return customerEmail
   */
  public String getCustomerEmail() {
    return customerEmail;
  }

  /**
   * Returns the project's Customer's physical address.
   * @return customerAddress
   */
  public String getCustomerAddress() {
    return customerAddress;
  }

  /**
   * Returns the project's Contractor's name.
   * @return contractorName
   */
  public String getContractorName() {
    return contractorName;
  }

  /**
   * Returns the project's Contractor's number.
   * @return contractorNumber
   */
  public String getContractorNumber() {
    return contractorNumber;
  }

  /**
   * Updates the project's Contractor's number.
   * @param contractorNumber The new contractor number.
   */
  public void setContractorNumber(String contractorNumber) {
    this.contractorNumber = contractorNumber;
  }

  /**
   * Returns the project's Contractor's email.
   * @return contractorEmail
   */
  public String getContractorEmail() {
    return contractorEmail;
  }

  /**
   * Updates the project's Contractor's email.
   * @param contractorEmail The new email address.
   */
  public void setContractorEmail(String contractorEmail) {
    this.contractorEmail = contractorEmail;
  }

  /**
   * Returns the project's Contractor's physical address.
   * @return contractorAddress
   */
  public String getContractorAddress() {
    return contractorAddress;
  }

  /**
   * Returns the project's total cost to customer.
   * @return totalProjectFee
   */
  public double getTotalProjectFee() {
    return totalProjectFee;
  }

  /**
   * Returns the current amount paid by the customer towards the project.
   * @return amountPaid
   */
  public double getAmountPaid() {
    return amountPaid;
  }

  /**
   * Updates the project fee paid by the customer.
   * @param amountPaid The new total amount paid by the customer.
   */
  public void setAmountPaid(double amountPaid) {
    this.amountPaid = amountPaid;
  }

  /**
   * Assigns the 'newName' parameter as the project name if a name isn't specified during the project creation.
   * @param newName The string concatenation of the project's 'buildingType' and customer's first name.
   */
  public void setProjectName(String newName) {

    this.projectName = newName;
  }

  /**
   * Updates the deadline of a user-chosen project.
   */
  public ArrayList<String> changeDeadline() {
    Scanner s = new Scanner(System.in);
    System.out.println("\nEnter the new project deadline below in the format(yyyy-mm-dd)");
    String newDeadline = s.nextLine();
    this.setDeadline(newDeadline);
    ArrayList<String> vals = new ArrayList<String>(Arrays.asList(newDeadline, String.valueOf(this.getProjectNumber())));
    return vals;
  }

  /**
   * Updates the amount paid by the customer towards the total project costs.
   */
  public ArrayList<String> updateAmountPaid() {
    Scanner s = new Scanner(System.in);
    System.out.println("\nEnter the new amount paid towards the project below (Include the two decimal places.)");
    double newAmountPaid = s.nextDouble();
    this.setAmountPaid(newAmountPaid);
    ArrayList<String> vals = new ArrayList<String>(Arrays.asList(String.valueOf(newAmountPaid), String.valueOf(this.getProjectNumber())));
    return vals;
  }

  /**
   * Updates the specific project's contractor information.
   * Updates the contractor's phone number, email address or both depending on the user's input.
   * @param x An arraylist of size two sent from the updateContractorDetails() Contractor subclass method.
   *          Holds either the updated phone number, email address or both depending on the values in x.
   *          These arraylist elements will be used to update the the project contractor details.
   */
  public void updateProjectContractor(ArrayList<String> x) {
    if (x.get(1).equals("0")) {
      this.setContractorNumber(x.get(0));
    } else if (x.get(0).equals("0")) {
      setContractorEmail(x.get(1));
    } else {
      this.setContractorNumber(x.get(0));
      this.setContractorEmail(x.get(1));
    }
  }

  /**
   * Updates the completion status to "Finalised" and will assign a completion date to the specific project.
   * If the total project fee has not been paid by the customer, the system will generate an invoice indicating the balance to be paid by the customer.
   */
  public ArrayList<String> finaliseProject() {
    // Updating project completionStatus and dateCompleted
    this.setCompletionStatus("finalised");
    LocalDate dateObject = LocalDate.now();     // Using the LocalDateTime module to create a date object
    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");     // Changing the date format
    this.setDateCompleted(dateObject.format(myFormat)); // Assigning the new date completed for the project
    System.out.println("\nProject " + this.getProjectName() + " has been finalised.");

    // Generating invoice
    if (this.getAmountPaid() < this.getTotalProjectFee()) {
      double amountDue = this.getTotalProjectFee() - this.getAmountPaid();
      // Printing  out Invoice
      System.out.println("\nDear " + this.getCustomerName() + ",\nThe total amount due from you for the completion of the " + this.getProjectName() + " project is : R " + String.format("%.2f", amountDue));
    }
    ArrayList<String> vals = new ArrayList<String>(Arrays.asList("finalised", String.valueOf(this.getDateCompleted()), String.valueOf(this.getProjectNumber())));
    return vals;
  }
  /**
   * Returns a string "projectSummary" that details all the relevant information about a project.
   * @return The string "projectSummary"
   */

  public String toString() {
    String projectSummary = "\nProject Number: " + getProjectNumber();
    projectSummary += "\nProject Name: " + getProjectName();
    projectSummary += "\nERF Number: " + getErfNumber();
    projectSummary += "\nBuilding Type: " + getBuildingType();
    projectSummary += "\nProject Address: " + getAddress();
    projectSummary += "\nTotal Project Fee: " + "R " + getTotalProjectFee();
    projectSummary += "\nAmount Paid: " + "R " + getAmountPaid();
    projectSummary += "\nDeadline: " + getDeadline();
    projectSummary += "\nCompletion Status: " + getCompletionStatus();
    projectSummary += "\nDate Completed: " + getDateCompleted();
    projectSummary += "\nArchitect Information";
    projectSummary += "\nProject Architect: " + getArchitectName();
    projectSummary += "\nArchitect's Phone Number: " + getArchitectNumber();
    projectSummary += "\nArchitect's Email Address: " + getArchitectEmail();
    projectSummary += "\nArchitect's Physical Address: " + getArchitectAddress();
    projectSummary += "\nContractor Information";
    projectSummary += "\nProject Contractor: " + getContractorName();
    projectSummary += "\nContractor's Phone Number: " + getContractorNumber();
    projectSummary += "\nContractor's Email Address: " + getContractorEmail();
    projectSummary += "\nContractor's Physical Address: " + getContractorAddress();
    projectSummary += "\nCustomer Information";
    projectSummary += "\nProject Customer: " + getCustomerName();
    projectSummary += "\nCustomer's Phone Number: " + getCustomerNumber();
    projectSummary += "\nCustomer's Email Address: " + getCustomerEmail();
    projectSummary += "\nCustomer's Physical Address: " + getCustomerAddress();

    return projectSummary;
  }

}
