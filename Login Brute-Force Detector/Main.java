import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Register and Login System with complete input and identity validation
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Registration database storage variables
        String regUsername = "";
        String regEmail = "";
        String regPassword = "";
        int regAge = 0;
        String regCountry = "";
        
        // Operational variables
        int option = 0;
        int failedAttempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        // --- MENU SCREEN ---
        boolean validMenuChoice = false;
        while (!validMenuChoice) {
            System.out.println("=== WELCOME TO PICASSO GAMING HUB ===");
            System.out.println("1: LogIn to Account");
            System.out.println("2: Register an Account\n");
            System.out.print("Choose an option from above (1 or 2): ");
            
            String optionInput = scanner.nextLine().trim();
            
            try {
                option = Integer.parseInt(optionInput);
                if (option == 1 || option == 2) {
                    validMenuChoice = true;
                } else {
                    System.out.println("\n[ERROR] Out of bounds. Please choose strictly 1 or 2.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Invalid format! Letters are not allowed.\n");
            }
        }
        
        // --- AUTOMATIC ROUTING ---
        if (option == 1) {
            System.out.println("\n[NOTICE] No accounts found in database. Redirecting to Registration...");
            option = 2; 
        }
        
        // --- STEP 1: REGISTRATION GATEWAY ---
        if (option == 2) {
            System.out.println("\n--- REGISTRATION GATEWAY ---");
            
            System.out.print("Create a Username: ");
            regUsername = scanner.nextLine().trim();
            
            // Fix 1: Bulletproof Email Validation Loop
            boolean validEmail = false;
            while (!validEmail) {
                System.out.print("Enter your Email: ");
                regEmail = scanner.nextLine().toLowerCase().trim();
                
                // Ensure it contains @, has characters before @, and has a domain after @
                if (regEmail.contains("@") && regEmail.indexOf("@") > 0 && regEmail.indexOf("@") < regEmail.length() - 1) {
                    validEmail = true;
                } else {
                    System.out.println("[ERROR] Invalid email format! Missing '@' or malformed structure.\n");
                }
            }
            
            System.out.print("Create a Secure Password: ");
            regPassword = scanner.nextLine().trim();
            
            // Age Validation Loop
            boolean validAgeInput = false;
            while (!validAgeInput) {
                System.out.print("Enter your Age: ");
                String ageInput = scanner.nextLine().trim();
                try {
                    regAge = Integer.parseInt(ageInput);
                    if (regAge >= 13 && regAge <= 120) {
                        validAgeInput = true;
                    } else {
                        System.out.println("[ERROR] Age must be between 13 and 120.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Age must be a numerical integer.\n");
                }
            }
            
            System.out.print("Enter your Country: ");
            regCountry = scanner.nextLine().trim();
            
            // CAPTCHA Human Verification Loop
            System.out.println("\n[SECURITY] Initiating Human Verification...");
            int generatedCaptcha = 100000 + random.nextInt(900000); 
            
            boolean captchaPassed = false;
            while (!captchaPassed) {
                System.out.println("Your 6-Digit Verification Code is: " + generatedCaptcha);
                System.out.print("Please re-enter the 6 digits to verify: ");
                String captchaInput = scanner.nextLine().trim();
                
                try {
                    int userCaptchaInput = Integer.parseInt(captchaInput);
                    if (userCaptchaInput == generatedCaptcha) {
                        captchaPassed = true;
                    } else {
                        System.out.println("\n[FAILED] Verification mismatched!\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n[FAILED] Invalid format! Numbers only.\n");
                }
            }
            
            System.out.println("\n[SUCCESS] Registration Complete! Redirecting to Login...\n");
        }
        
        // --- STEP 2: SECURE LOGIN GATEWAY ---
        System.out.println("--- SECURE SYSTEM LOGIN ---");
        
        // Fix 2: Prompt for username ONCE before entering the password loop
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine().trim();
        
        // Security Check: If username doesn't exist, terminate instantly or lock down
        if (!loginUser.equals(regUsername)) {
            System.out.println("\n[ALERT] SECURITY ACCESS DENIED!");
            System.out.println("The username '" + loginUser + "' does not exist in our registry database.");
            failedAttempts = MAX_ATTEMPTS; // Instantly lock out
        }
        
        // Password Attempt Loop for the verified username
        while (failedAttempts < MAX_ATTEMPTS) {
            System.out.print("Enter Password for " + loginUser + ": ");
            String loginPass = scanner.nextLine().trim();
            
            if (loginPass.equals(regPassword)) {
                System.out.println("\n=========================================");
                System.out.println("ACCESS GRANTED: Welcome to Picasso Gaming Hub!");
                System.out.println("Profile Loaded: " + regUsername + " (" + regCountry + ", Age: " + regAge + ")");
                System.out.println("=========================================");
                break;
            } else {
                failedAttempts++;
                int remaining = MAX_ATTEMPTS - failedAttempts;
                System.out.println("\n[WARNING] Incorrect password match.");
                if (remaining > 0) {
                    System.out.println("You have " + remaining + " attempts remaining.\n");
                }
            }
        }
        
        if (failedAttempts == MAX_ATTEMPTS) {
            System.out.println("\n[ALERT] SECURITY ACCOUNT LOCKDOWN!");
            System.out.println("Authentication failure threshold exceeded. Intrusion prevention active.");
        }
        
        scanner.close();
    }
}
