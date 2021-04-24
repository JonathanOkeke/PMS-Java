import java.text.ParseException;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.sql.*;
/**
 * The Poised class represents the actual project management software tool that the company employees will be using to keep track of ,add and edit projects in the POISEPMS SQL database and its respective tables.
 *
 * @author Jonathan Okeke
 * @version 4.0 April 7, 2021
 */

public class Poised {

  public static ArrayList<Project> projectObjectList = new ArrayList<Project>();  // A list that will hold all of project objects as entries that can be individually accessed.
  public static ArrayList<ArrayList<Object>> projectList = new ArrayList<ArrayList<Object>>();  // A list of lists that will hold all of the information of each project as a list.
  public static ArrayList<ArrayList<Object>> dbProjectList = new ArrayList<ArrayList<Object>>(); // A list of list that will be used to read and write project information to and from the database
  public static ArrayList<Person> peopleList = new ArrayList<Person>();   // A list that will hold all of the created architect, contractor and customer objects and their corresponding information as individual list entries.
  public static ArrayList<ArrayList<Object>> architectList = new ArrayList<ArrayList<Object>>();
  public static ArrayList<ArrayList<Object>> customerList = new ArrayList<ArrayList<Object>>();
  public static ArrayList<ArrayList<Object>> contractorList = new ArrayList<ArrayList<Object>>();
  public static Scanner s = new Scanner(System.in);

  /**
   * Updates the list of lists variable "projectList".
   * Updates old projects that have been edited by the user.
   * Adds newly created projects as a new lists to the "projectList".
   * @param projectObjectList An arraylist that holds all the instantiated project objects as list entries.
   */
  public static void updateProjectList(ArrayList<Project> projectObjectList) {
    // Start by clearing the projectList
    projectList.clear();
    for (Project project : projectObjectList) {
      ArrayList<Object> singleList = new ArrayList<>();
      singleList.add(project.getProjectNumber());
      singleList.add(project.getProjectName());
      singleList.add(project.getErfNumber());
      singleList.add(project.getBuildingType());
      singleList.add(project.getAddress());
      singleList.add(project.getTotalProjectFee());
      singleList.add(project.getAmountPaid());
      singleList.add(project.getDeadline());
      singleList.add(project.getCompletionStatus());
      singleList.add(project.getDateCompleted());
      singleList.add(project.getArchitectName());
      singleList.add(project.getArchitectNumber());
      singleList.add(project.getArchitectEmail());
      singleList.add(project.getArchitectAddress());
      singleList.add(project.getContractorName());
      singleList.add(project.getContractorNumber());
      singleList.add(project.getContractorEmail());
      singleList.add(project.getContractorAddress());
      singleList.add(project.getCustomerName());
      singleList.add(project.getCustomerNumber());
      singleList.add(project.getCustomerEmail());
      singleList.add(project.getCustomerAddress());
      // Adding this list to the list of lists
      projectList.add(singleList);
    }
  }

  /**
   * Displays the current projects and the project-related information.
   */
  public static void viewProjects() {
    System.out.println("\nEnter va - To view all projects\nEnter e - To view incomplete projects\nEnter l - To view overdue projects\nEnter s - To view a specific project");
    String viewOption = s.nextLine();

    // Printing out all current projects
    if (viewOption.equalsIgnoreCase("va")) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        System.out.println("\n" + projectObjectList.get(i));
      }
    }
    // Printing out incomplete projects
    else if (viewOption.equalsIgnoreCase("e")) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        if (projectObjectList.get(i).getCompletionStatus().equals("incomplete")) {
          System.out.println("\n" + projectObjectList.get(i));
        }
      }
    }
    // Printing overdue projects
    else if (viewOption.equalsIgnoreCase("l")) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        // Converting the deadline string assigned to each project into a date object
        //String stringDueDate = projectObjectList.get(i).deadline;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(projectObjectList.get(i).getDeadline(), formatter);
        LocalDate today = LocalDate.now();  // Using the LocalDateTime module to create a date object for today
        boolean isOverdue = dueDate.isBefore(today);    // Determining whether dueDate for for the project has passed or not
        if (isOverdue) {
          System.out.println("\n" + projectObjectList.get(i));
        }
      }
    }
    // Printing a user chosen project
    else if (viewOption.equalsIgnoreCase("s")) {
      System.out.println("Enter the project name or project number of the project you want to view below:");
      String projectID = s.nextLine();
      int counter = 0;
      boolean stop = false;
      while (!stop) {
        for (int i = 0; i < projectObjectList.size(); i++) {
          // Exception handling: Because I am parsing the string 'projectID' to an integer, I needed to handle the potential problem where the user inputs a string wih no digits in it, which would cause th program to crash.
          try {
            if (projectID.equals(projectObjectList.get(i).getProjectName()) || Integer.parseInt(projectID) == (projectObjectList.get(i).getProjectNumber())) {    //Here I check if the user input string 'projectID' either matches any projects names stored in the system or any project numbers in the system after converting it to an integer
              System.out.println(projectObjectList.get(i)); // Printing the specified project
              stop = true;
            } else {
              counter++;
            }
          }
          catch (Exception e) {
            counter++;  // Continue counting until counter equals the size of the projectObjectList whereby the while loop will be broken.
          }
        }
        if (counter == projectObjectList.size()) {
          System.out.println("\nThe project name or project number you entered does not exist.");
          stop = true;
        }
      }
    }
  }

  /**
   * Displays all persons directly involved with a specific project.
   */
  public static void viewPeople() {
    System.out.println("\nEnter 1 - To view all architects, contractors and customers.\nEnter 2 - To view the architect, contractor and customer for a specific project.");
    String personChoice = s.nextLine();
    switch (personChoice) {
      case "1":
        for (int i = 0; i < peopleList.size(); i++) {
          System.out.println("\n" + peopleList.get(i));
        }
        break;

      case "2":
        System.out.println("\nEnter the project number of interest below:");
        try {
          int projectNum = s.nextInt();
          int counter = 0;
          for( Project project : projectObjectList) {
            if (projectNum == project.getProjectNumber()) {
              System.out.println("\nProject " + projectNum + " Info:\n" + "Architect: " + project.getArchitectName() + "\nContractor: " + project.getContractorName() + "\nCustomer: " + project.getCustomerName());
            } else {
              counter++;
            }
            if (counter == projectObjectList.size()) {
              System.out.println("\nProject does not exist!");
              break;
            }
          }
        }
        catch (InputMismatchException i) {
          System.out.println("\nInvalid Project Number!\nOnly enter digits when specifying the project number.");
          break;
        }
        break;
    }
  }

  /**
   * Allows user to create a new project.
   * Will create a project object and update the project list as well as the SQL project's table.
   * Will also create the relevant person object's related to the project.
   * @param statement
   * @throws ParseException
   * @throws SQLException
   */
  public static void addNewProject(Statement statement) throws ParseException, SQLException {
    ArrayList<Object> newProjectDetails = new ArrayList<>();   // A list that will temporarily hold the details of a newly defined project
    ArrayList<Object> archList = new ArrayList<>();
    ArrayList<Object> conList = new ArrayList<>();
    ArrayList<Object> cuList = new ArrayList<>();
    int projectNumber = (projectObjectList.size() + 1);
    newProjectDetails.add(projectNumber);
    System.out.println("\nEnter the project name below: ");
    String projectName = s.nextLine();
    newProjectDetails.add(projectName);
    System.out.println("Enter the project ERF number below: ");
    int erfNumber = s.nextInt();
    s.nextLine();
    newProjectDetails.add(erfNumber);
    System.out.println("Enter the project build type below: ");
    String buildingType = s.nextLine();
    newProjectDetails.add(buildingType);
    System.out.println("Enter the project building address below: ");
    String address = s.nextLine();
    newProjectDetails.add(address);
    System.out.println("Enter the total project fee below: ");
    double totalFee = s.nextDouble();
    newProjectDetails.add(totalFee);
    System.out.println("Enter the current amount paid towards the project below: ");
    double feePaid = s.nextDouble();
    s.nextLine();
    newProjectDetails.add(feePaid);
    System.out.println("Enter the project's deadline (yyyy-MM-dd) below: ");
    String deadline = s.nextLine();
    newProjectDetails.add(deadline);
    newProjectDetails.add("incomplete");  // Completion status field.
    newProjectDetails.add("-");   // Date completed field.
    // Architect Details
    System.out.println("Enter the architect's full name below in the format(Name Surname)");
    String architectName = s.nextLine();
    newProjectDetails.add(architectName);
    archList.add(architectName);
    System.out.println("Enter the architect's phone number below and include the country code (SA country code = +27)");
    String architectNumber = s.nextLine();
    archList.add(architectNumber);
    System.out.println("Enter the architect's email address below");
    String architectEmail = s.nextLine();
    archList.add(architectEmail);
    System.out.println("Enter the architect's physical address below");
    String architectAddress = s.nextLine();
    archList.add(architectAddress);
    architectList.add(archList);
    // Contractor Details
    System.out.println("Enter the contractor's full name below in the format(Name Surname)");
    String contractorName = s.nextLine();
    newProjectDetails.add(contractorName);
    conList.add(contractorName);
    System.out.println("Enter the contractor's phone number below and include the country code (SA country code = +27");
    String contractorNumber = s.nextLine();
    conList.add(contractorNumber);
    System.out.println("Enter the contractor's email address below");
    String contractorEmail = s.nextLine();
    conList.add(contractorEmail);
    System.out.println("Enter the contractor's physical address below");
    String contractorAddress = s.nextLine();
    conList.add(contractorAddress);
    contractorList.add(conList);
    // Customer Details
    System.out.println("Enter the customer's full name below in the format(Name Surname)");
    String customerName = s.nextLine();
    newProjectDetails.add(customerName);
    cuList.add(customerName);
    System.out.println("Enter the customer's phone number below and include the country code (SA country code = +27");
    String customerNumber = s.nextLine();
    cuList.add(customerNumber);
    System.out.println("Enter the customer's email address below");
    String customerEmail = s.nextLine();
    cuList.add(customerEmail);
    System.out.println("Enter the customer's physical address below");
    String customerAddress = s.nextLine();
    cuList.add(customerAddress);
    customerList.add(cuList);

    // Creating a new project object
    Project newProject = new Project(newProjectDetails,architectList,contractorList, customerList);
    // Adding new project object to the projectObjectList
    projectObjectList.add(newProject);

    // Setting the newProject's name
    if (projectName.equals(""))         // Handles case when user does not input any project name
    {
      //Getting the Customer Surname
      int index = customerName.indexOf(" ");
      String surname = customerName.substring(index);     // Slicing out the surname of the customer
      projectName = buildingType + "" + surname;
      newProject.setProjectName(projectName);
    }

    // Adding each project's information as a list into the list of lists
    updateProjectList(projectObjectList);
    // Updating the txtProjectList
    dbProjectList.clear();
    dbProjectList.addAll(projectList);

    // Adding new project, architect, contractor and customer details to their respective database tables
    String action1 = "INSERT INTO projects VALUES (" + projectNumber + "," + " '" + projectName + "', " + erfNumber + ", " + "'" + buildingType + "', " + "'" + address + "', " + totalFee + ", " + feePaid + ", " + "'" + deadline + "', " + "'incomplete'" + ", " + "null" + ", " + "'" + architectName + "', " + "'" + contractorName + "', " + "'" + customerName + "'" + ")";
    statement.executeUpdate(action1);

    //Creating new Person objects and adding them to the peopleList
    Architect newArchitect = new Architect(architectName, architectNumber, architectEmail, architectAddress);
    peopleList.add(newArchitect);
    Contractor newContractor = new Contractor(contractorName, contractorNumber, contractorEmail, contractorAddress);
    peopleList.add(newContractor);
    Customer newCustomer = new Customer(customerName, customerNumber, customerEmail, customerAddress);
    peopleList.add(newCustomer);

    // Clearing the NewProjectList so that when the addNewProject method is called again there won't be any prior data overlap.
    newProjectDetails.clear();
  }

  /**
   * Updates a specified project's deadline both in the project's SQL table and in the project list.
   * @param statement
   * @throws SQLException
   */
  public static void updateProjectDeadline(Statement statement) throws SQLException {
    System.out.println("\nEnter the name or number of the project of which you want to change the deadline below:");
    String projectID = s.nextLine();
    String newDeadline = "";
    ArrayList<String> vals = new ArrayList<String>();
    int counter = 0;
    boolean stop = false;
    while (!stop) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        try {
          if (projectID.equalsIgnoreCase(projectObjectList.get(i).getProjectName()) || Integer.parseInt(projectID) == (projectObjectList.get(i).getProjectNumber())) {  //Here I check if the user input string 'nameOfProject' matches any projects names stored in the system.
            vals = projectObjectList.get(i).changeDeadline();  // Changing the chosen project's deadline by calling the changeDeadline Method
            stop = true;
          } else {
            counter++;
          }
        }
        catch (Exception e) {
          counter++;
        }
      }
      if (counter == projectObjectList.size()) {
        System.out.println("\nThe project name you entered does not exist.");
        stop = true;
      }
    }
    // Updating the list of lists that holds the project information
    updateProjectList(projectObjectList);
    // Updating the txtProjectList
    dbProjectList.clear();
    dbProjectList.addAll(projectList);
    // Updating project deadline in SQL Database
    String action = "UPDATE projects SET Deadline = " + "'" + vals.get(0) + "'" + " WHERE Project_Number = " + vals.get(1);
    statement.executeUpdate(action);
  }

  /**
   * Updates the amount the amount paid a customer to their related project.
   * These changes will be reflected in the project's SQL table.
   * @param statement
   * @throws SQLException
   */
  public static void updateProjectFeePaid(Statement statement) throws SQLException {
    System.out.println("\nEnter the name or number of the project of which you want to update the amount paid below:");
    String projectID = s.nextLine();
    ArrayList<String> vals = new ArrayList<String>();
    boolean stop = false;
    int counter = 0;
    while (!stop) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        try {
          //Here I check if the user input string 'nameOfProject' matches any projects names stored in the system.
          if (projectID.equalsIgnoreCase(projectObjectList.get(i).getProjectName()) || Integer.parseInt(projectID) == (projectObjectList.get(i).getProjectNumber())) {
            vals = projectObjectList.get(i).updateAmountPaid();    // Changing the chosen project's fee paid by calling the updateAmountPaid Method
            stop = true;
          } else {
            counter++;
          }
        }
        catch (Exception e) {
          counter++;
        }
      }
      if (counter == projectObjectList.size()) {
        System.out.println("\nThe project name you entered does not exist.");
        stop = true;
      }
    }
    // Updating the list of lists that holds the project information
    updateProjectList(projectObjectList);
    // Updating the txtProjectList
    dbProjectList.clear();
    dbProjectList.addAll(projectList);
    // Updating SQL table
    String action = "UPDATE projects SET Total_Amount_Paid = " + vals.get(0) + " WHERE Project_Number = " + vals.get(1);
    statement.executeUpdate(action);
  }

  /**
   * Update the contact information for a specified Contractor.
   * Changes will also reflect in the contractor's SQL table.
   * @param statement
   * @throws SQLException
   */
  public static void updateContractorContactInfo(Statement statement) throws SQLException {
    System.out.println("\nEnter the full name of the Contractor for whom you want to update their contact details below:");
    String inputName = s.nextLine();
    ArrayList<String> detailChanges = new ArrayList<String>();
    int counter = 0;
    boolean stop = false;
    while (!stop) {
      // Updating the Contractor object's information
      for (int i = 0; i < peopleList.size(); i++) {
        //Here I check if the user input string 'inputName' matches any contractor names stored in the system.
        if (inputName.equalsIgnoreCase(peopleList.get(i).getName())) {
          ArrayList<String> contactChanges = ((Contractor) peopleList.get(i)).updateContractorDetails();        // Changing the chosen contractor's details by calling the updateContractorDetails Method
          detailChanges = (ArrayList<String>) contactChanges.clone();                                          // Receiving the new contractors new phone number and email in order to update project details
          stop = true;
          break;
        } else {
          counter++;
        }
      }
      if (counter == peopleList.size()) {
        System.out.println("\nThe Contractor name you entered does not exist.");
        break;
      }
    }
      // Updating the Contractors information in the Project that they are assigned to
      for (int i = 0; i < projectObjectList.size(); i++) {
        if (inputName.equalsIgnoreCase(projectObjectList.get(i).getContractorName())) {             //Here I check if the user input string 'nameOfProject' matches any projects names stored in the system.
          projectObjectList.get(i).updateProjectContractor(detailChanges);        // Changing the chosen project's fee paid by calling the updateAmountPaid Method
        }
      }
      // Updating the list of lists that holds the project information
      updateProjectList(projectObjectList);
      // Updating the txtProjectList
      dbProjectList.clear();
      dbProjectList.addAll(projectList);
      // Updating SQL table
      if (detailChanges.get(0).equalsIgnoreCase("0")){
        String action = "UPDATE contractors SET Email_Address = " + "'" + detailChanges.get(1) + "'" + " WHERE Contractor_Name = " + "'" + detailChanges.get(2) + "'";
        statement.executeUpdate(action);
      } else if(detailChanges.get(1).equalsIgnoreCase("0")){
        String action = "UPDATE contractors SET Phone_Number = " + "'" + detailChanges.get(0) + "'" + " WHERE Contractor_Name = " + "'" + detailChanges.get(2) + "'";
        statement.executeUpdate(action);
      } else {
        String action1 = "UPDATE contractors SET Email_Address = " + "'" + detailChanges.get(1) + "'" + " WHERE Contractor_Name = " + "'" + detailChanges.get(2) + "'";
        statement.executeUpdate(action1);
        String action2 = "UPDATE contractors SET Phone_Number = " + "'" + detailChanges.get(0) + "'" + " WHERE Contractor_Name = " + "'" + detailChanges.get(2) + "'";
        statement.executeUpdate(action2);
      }
  }

  /**
   * Finalises a project specified by the user.
   * This change will be reflected by updating the specific's projects Completion_Status to 'finalised' and update the project's Date_Completed field.
   * Will generate an invoice if the customer has not paid the total project fee.
   * @param statement
   * @throws SQLException
   */
  public static void finaliseProject(Statement statement) throws SQLException {
    System.out.println("\nEnter the project name or project number of the project you want to finalise below:");
    String projectID = s.nextLine();
    ArrayList<String> vals = new ArrayList<String>();
    int counter = 0;
    boolean stop = false;
    while (!stop) {
      for (int i = 0; i < projectObjectList.size(); i++) {
        try {
          if (projectID.equalsIgnoreCase(projectObjectList.get(i).getProjectName())) {    //Here I check if the user input string 'projectID'  matches any projects names stored in the system
            vals = projectObjectList.get(i).finaliseProject();        // Calling the finaliseProject Method
            stop = true;

            //Here I first parse the projectID string into an integer and check if it is equal to any project number in the system
          } else if (Integer.parseInt(projectID) == (projectObjectList.get(i).getProjectNumber()))  {
            vals = projectObjectList.get(i).finaliseProject();        // Calling the finaliseProject Method
            stop = true;
          } else {
            counter++;
          }
        }
        catch (Exception e) {   // Because I am parsing the string 'projectID' to an integer, I needed to handle the potential problem where the user inputs a string wih no digits in it, which would cause th program to crash.
          counter++;           // Continue counting until counter equals the size of the projectObjectList whereby the while loop will be broken.
        }
      }
      if (counter == projectObjectList.size()) {
        System.out.println("\nThe project number or project name you entered does NOT exist.");
        stop = true;
      }
    }
    // Updating the list of lists that holds the project information
    updateProjectList(projectObjectList);
    // Updating the txtProjectList
    dbProjectList.clear();
    dbProjectList.addAll(projectList);
    // Updating project deadline in SQL Database
    String action = "UPDATE projects SET Completion_Status = " + "'" + vals.get(0) + "'" + " WHERE Project_Number = " + vals.get(2);
    statement.executeUpdate(action);
    String action1 = "UPDATE projects SET Date_Completed = " + "'" + vals.get(1) + "'" + " WHERE Project_Number = " + vals.get(2);
    statement.executeUpdate(action1);
  }

  // Main Method
  public static void main(String[] args) {
    // Reading from the text file and updating the txtProjectList parent list.
    try {
      // Connecting to my library_db SQL database via the jdbc:mysql: channel on localhost
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
          "jon",
          "3188");
      // Create a direct line to the database for running database queries
      Statement statement = connection.createStatement();
      // Reading from the projects database table and filling the dbProjectList list of lists
      ResultSet result1 = statement.executeQuery("SELECT * FROM projects");
      while (result1.next()) {
        ArrayList<Object> recordList = new ArrayList<>(Arrays.asList(result1.getInt("Project_Number"),
            result1.getString("Project_Name"),
            result1.getInt("ERF_Number"),
            result1.getString("Building_Type"),
            result1.getString("Physical_Address"),
            result1.getDouble("Total_Project_Cost"),
            result1.getDouble("Total_Amount_Paid"),
            result1.getDate("Deadline"),
            result1.getString("Completion_Status"),
            result1.getDate("Date_Completed"),
            result1.getString("Architect_Name"),
            result1.getString("Contractor_Name"),
            result1.getString("Customer_Name")));
        dbProjectList.add(recordList);
      }

      // Reading from the architects database table and filling the architectList
      ResultSet result2 = statement.executeQuery("SELECT * FROM architects");
      while (result2.next()){
        ArrayList<Object> aList = new ArrayList<>(Arrays.asList(
            result2.getString("Architect_Name"),
            result2.getString("Phone_Number"),
            result2.getString("Email_Address"),
            result2.getString("Physical_Address")));
        architectList.add(aList);
      }

      // Reading from the contractors database table and filling the contractorList
      ResultSet result3 = statement.executeQuery("SELECT * FROM contractors");
      while (result3.next()){
        ArrayList<Object> coList = new ArrayList<>(Arrays.asList(
            result3.getString("Contractor_Name"),
            result3.getString("Phone_Number"),
            result3.getString("Email_Address"),
            result3.getString("Physical_Address")));
        contractorList.add(coList);
      }

      // Reading from the customers database table and filling the customerList
      ResultSet result4 = statement.executeQuery("SELECT * FROM customers");
      while (result4.next()){
        ArrayList<Object> cuList = new ArrayList<>(Arrays.asList(
            result4.getString("Customer_Name"),
            result4.getString("Phone_Number"),
            result4.getString("Email_Address"),
            result4.getString("Physical_Address")));
        customerList.add(cuList);
      }

      // Creating Project objects from the dbProjectList list of lists.
      for (ArrayList<Object> entry : dbProjectList) {
        Project newProject = new Project(entry,architectList,contractorList,customerList);
        // Updating projectObjectList
        projectObjectList.add(newProject);
      }
      // Adding each project information as a list into the list of lists (projectList)
      updateProjectList(projectObjectList);

      // Creating the People objects from the architectList, contractorList and customerList parent lists
      for (int i = 0; i < architectList.size(); i++){
        Architect architect = new Architect(String.valueOf(architectList.get(i).get(0)), String.valueOf(architectList.get(i).get(1)), String.valueOf(architectList.get(i).get(2)), String.valueOf(architectList.get(i).get(3)));
        Contractor contractor = new Contractor(String.valueOf(contractorList.get(i).get(0)), String.valueOf(contractorList.get(i).get(1)), String.valueOf(contractorList.get(i).get(2)), String.valueOf(contractorList.get(i).get(3)));
        Customer customer = new Customer(String.valueOf(customerList.get(i).get(0)), String.valueOf(customerList.get(i).get(1)), String.valueOf(customerList.get(i).get(2)), String.valueOf(customerList.get(i).get(3)));
        // Updating the peopleList that holds all of the various Person objects as individual entries in the list.
        peopleList.add(architect);
        peopleList.add(contractor);
        peopleList.add(customer);
      }

      // System Loop
      while (true) {
        System.out.println("\nEnter 1 - To view projects.\nEnter 2 - To view persons related to current projects.\nEnter 3 - To add a new project.\nEnter 4 - To update the deadline of a specific project.\nEnter 5 - To update the amount of total project fee paid towards a certain project.\nEnter 6 - To change a contractor's contact details.\nEnter 7 - To finalise a project.\nEnter 8 - To exit.");
        System.out.print("Enter your option here: ");
        String userInput = s.nextLine();

        if (userInput.equals("1")) {
          viewProjects();
        }

        // Printing out all persons related to any project
        else if (userInput.equals("2")) {
          viewPeople();
        }

        // Capturing project details
        else if (userInput.equals("3")) {
          addNewProject(statement);
        }
        // Updating a projects deadline
        else if (userInput.equals("4")) {
          updateProjectDeadline(statement);
        }
        // Changing the total amount of fee paid
        else if (userInput.equals("5")) {
          updateProjectFeePaid(statement);
        }

        // Updating the Contractors details
        else if (userInput.equals("6")) {
          updateContractorContactInfo(statement);
        }

        // Finalising a project
        else if (userInput.equals("7")) {
          finaliseProject(statement);
        }
        // Closing the program
        else if (userInput.equals("8")) {
          // Close up database connections
          result1.close();
          result2.close();
          result3.close();
          statement.close();
          connection.close();
          break;
        }
      }
    }
    catch (SQLException | ParseException se){
      // Printing out only SQLException related errors
      se.printStackTrace();
    }
  }
}

