import java.util.Scanner;

public class StudentValidation {
    public static void main(String[] args) {
        // Program that validates if the details entered are for a student
        // Strictly with no vulnerabilities
        
        // Initialising the scanner
        Scanner scanner = new Scanner(System.in);
        
        // Declaring variables
        String email;
        String tempNo;
        int studentNo = 0; // Initialised to 0 to prevent compiler errors
        String domain;
        
        // We use a flag to track if the ENTIRE validation passed successfully
        boolean isValidStudent = false;
        
        // This loop keeps running until EVERYTHING is perfectly valid
        while (!isValidStudent) {
            
            // Asking the user to input the student email
            System.out.print("Enter your student email: ");
            email = scanner.nextLine().toLowerCase().trim(); // Sanitise input instantly
            
            // 1. Vulnerability Fix: Ensure '@' exists and domain is strictly tut4life.ac.za
            if (email.contains("@")) {
                domain = email.substring(email.indexOf("@") + 1);
                
                if (domain.equals("tut4life.ac.za")) {
                    
                    // 2. Extracting number from email
                    tempNo = email.substring(0, email.indexOf("@"));
                    
                    // 3. Checking if the first portion before the @ is int and exactly 9 digits
                    try {
                        // This tries to parse the integer
                        studentNo = Integer.parseInt(tempNo); 
                        
                        // Security Check: Ensure length is exactly 9 digits (TUT Standard)
                        if (tempNo.length() == 9) {
                            isValidStudent = true; // Everything passed! Break the loop.
                        } else {
                            System.out.println("Error: A TUT student number must be exactly 9 digits long.\n");
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Student emails conntains student Number first.\n");
                    }
                    
                } else {
                    System.out.println("Error: Invalid domain. Must be @tut4life.ac.za\n");
                }
            } else {
                System.out.println("Error: Invalid email format. Missing '@' symbol.\n");
            }
        }
        
        // --- OUTPUT ---
        // 
        System.out.println("\n=================================");
        System.out.println("ACCESS GRANTED: Student Verified!");
        System.out.println("Validated Student Number: " + studentNo);
        System.out.println("=================================");
        
        // Closing the scanner
        scanner.close();
    }
}
