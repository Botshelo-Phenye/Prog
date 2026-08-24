/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.medicare_hospital;

import java.util.Scanner;




/**
 *
 * @author phenb
 */
public class MediCare_Hospital {

     
   

    public static void main(String[] args) {
  
     Scanner input = new Scanner(System.in);
        PatientManagement pm = new PatientManagement();
        Bedmanagement bm=new Bedmanagement();
        Reports reports = new Reports(pm,bm);
       
        
    
        int choice;

        do {
            System.out.println("\n=== Medicare Hospital System ===");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Allocate Bed");
            System.out.println("7. Release Bed");
            System.out.println("8. Display Ward Layout");
            System.out.println("9. Reports");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID:e.g P001 ");//Give the User an Example of how theyshould write the PatientID
                    String id = input.nextLine();
                    if (pm.search(id)!= null){
                        System.out.println("Error Patient ID already exists!");
                        break;
                    }
                    System.out.print("Enter First Name: ");
                    String firstName = input.nextLine();
                    if (firstName.isEmpty()){//Making the GUI user friendly to avoid inaccurate information
                        System.out.println("Error first name cannot be Empty ");
                    }
                    System.out.print("Enter Last Name: ");
                    String lastName = input.nextLine();
                    if (lastName.isEmpty()){//Making the GUI user friendly to avoid inaccurate information
                        System.out.println("Error last name cannot be Empty ");break;
                    }
                    System.out.print("Enter Age: ");
                    
                    while (!input.hasNextInt()){
                        System.out.println("Invalid age. Please enter a number ");
                        input.next();
                    }
                    int age = input.nextInt();
                    input.nextLine();
                    if (age <=0||age >120){//ensuring that the Age is between 1 and 120
                        System.out.println("Age must be between 1 and 120");  break;
                    }
                    System.out.print("Enter Gender: ");
                    String gender = input.nextLine();
                    System.out.print("Enter Medical Condition: ");
                    String condition = input.nextLine();
                    if (condition.isEmpty()){
                        System.out.println("Please fill it in ");break;
                       
                    }
                    System.out.print("Enter Category (Inpatient/Outpatient/Emergency): ");
                    String categoryInput = input.nextLine().toUpperCase();
                    

                    PatientCategory category = PatientCategory.valueOf(categoryInput);

                    if (category == PatientCategory.INPATIENT) {
                        System.out.print("Enter Ward Number: ");
                        String ward = input.nextLine();
                        System.out.print("Enter Bed Number: ");
                        String bed = input.nextLine();
                        pm.registerPatient(new Inpatient(id, firstName, lastName, age, gender, condition, ward, bed));
                        bm.allocateBed(id); // allocate automatically
                    } else {
                        pm.registerPatient(new Patient(id, firstName, lastName, age, gender, condition, category));
                    }
                    break;

                case 2:
                    System.out.print("Enter Patient ID to search: ");
                    String searchId = input.nextLine();
                    Patient found = pm.search(searchId);
                    if (found != null) {
                        found.toString();
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Patient ID to update: ");
                    String updateId = input.nextLine();
                    if (updateId==null){
                        System.out.println("Patient not found");break;
                    }
                    System.out.print("Enter new Medical Condition: ");
                    String newCondition = input.nextLine();
                    System.out.print("Enter new Category (Inpatient/Outpatient/Emergency): ");
                    String newCategoryInput = input.nextLine().toUpperCase();// convert to Upper case
                    PatientCategory newCategory = PatientCategory.valueOf(newCategoryInput);
                    pm.updatePatient(updateId, newCondition, newCategory);
                    break;

                case 4:
                    System.out.print("Enter Patient ID to delete: ");
                    String deleteId = input.nextLine();
                    pm.delete(deleteId);
                    bm.releaseBed(deleteId); // free bed if inpatient
                    break;

                case 5:
                    pm.displayPatients();
                    break;

                case 6:
                    System.out.print("Enter Patient ID to allocate bed: ");
                    String allocId = input.nextLine();
                    bm.allocateBed(allocId);
                    break;

                case 7:
                    System.out.print("Enter Patient ID to release bed: ");
                    String releaseId = input.nextLine();
                    bm.releaseBed(releaseId);
                    break;

                case 8:
                    bm.displayWardLayout();
                    break;

                case 9:
                    reports.generateReports();
                    break;

                case 10:
                    System.out.println("Exiting program... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);//Close the program

        input.close();
    }
}

        
        
        
    


    
        

         
       

 