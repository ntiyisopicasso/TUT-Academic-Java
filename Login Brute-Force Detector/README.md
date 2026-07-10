# 🎮 Picasso Gaming Hub - Vulnerability-Free Authentication Gateway

An enterprise-grade console-based registration and secure login application written in Java. This project demonstrates high-level defensive programming mechanisms designed to prevent standard application crashes, input injection exploits, and credential-stuffing logical bypasses.

## 🎯 Project Objective
The system serves as a secure gateway for a gaming application. It forces users to go through a strict validation chain during registration (including input structural testing and multi-factor bot protection) and seals identity tracking boundaries during authentication to completely block brute-force or context-switching exploits.

## 🔒 Security Implementations & Vulnerability Patches
This project was engineered from the ground up to neutralize common beginner programming flaws and software vulnerabilities:

* **Crash-Proof Input Stream Processing:** Completely eliminates `scanner.nextInt()` to prevent `InputMismatchException` crashes. All telemetry data is captured strictly as text streams (`scanner.nextLine()`) and securely evaluated using isolated parsing tokens.
* **Unified Try-Catch Casting:** Traps all numerical input fields (such as Menu Selection and Age metrics) inside robust `try-catch` structures. This absorbs alphabetic or symbolic injections gracefully without causing a runtime environment halt.
* **Structural Email Bound Verification:** Enforces structural integrity controls on emails via dynamic string indexing. The validator ensures an `@` symbol exists, verifying it is neither truncated at the start index (`index > 0`) nor left dangling at the trailing boundary.
* **Algorithmic Bot Defences (CAPTCHA):** Implements pseudorandom integer synthesis (`java.util.Random`) to generate an immutable 6-digit challenge token, blocking automated bot account creations.
* **Login Username Anchoring (Session Fixing Patch):** Anchors the identity parameter *before* entering the password validation matrix. If an unregistered username is entered, the session terminates immediately. This prevents an attacker from switching target identities halfway through an active authentication loop.

## 🛠️ Tech Stack & Methods Used
* **Language:** Java SE ☕
* **Utilities:** `java.util.Scanner`, `java.util.Random`
* **Core Logic:** String manipulation, Explicit Type Casting (`Integer.parseInt`), Nested Selection Loops (`while`), Structured Exception Handling (SEH).

## 🚀 How to Run and Test
1. Clone this repository or download the `Main.java` file.
2. Compile the application via your terminal:
   ```bash
   javac Main.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

### 🧪 Test Matrix (Validated Boundaries)
* **Type Injections:** Inputting `"twenty"` or `"abc"` into Menu/Age prompts ➡️ **Handled Gracefully (No Crash)**
* **Email Truncation:** Inputting `@gmail.com` or `user@` ➡️ **Blocked (Validation Loop Resets)**
* **Identity Context Switching:** Submitting a correct password against a non-existent username ➡️ **Instantly Terminated (Lockdown Active)**
* **Brute-Force Threshold:** Exceeding 3 consecutive password mismatches ➡️ **Account Temporary Lockdown Enforced**
