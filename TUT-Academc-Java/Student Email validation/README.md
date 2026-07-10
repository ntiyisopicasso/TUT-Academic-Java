# 🛡️ Secure TUT Student Email Validator

A robust, enterprise-grade Java application designed to securely parse and validate institutional student credentials. This project demonstrates defensive programming principles by ensuring user inputs are strictly structured, thoroughly sanitized, and completely immune to runtime crashes or logical bypasses.

## 🎯 Project Objective
The script acts as a secure gatekeeper. It forces users to input an official Tshwane University of Technology (TUT) student email (`@tut4life.ac.za`), extracts the numerical student number, validates its structure, and safely converts it into a usable integer format without risking system instability.

## 🔒 Implemented Security Features
To achieve a vulnerability-free architecture, the code implements the following defensive layers:

* **Instant Input Sanitization:** Utilizes `.toLowerCase()` and `.trim()` immediately upon capture. This strips malicious or accidental whitespace (common on mobile keyboards) and standardizes text casing to neutralize bypass attempts.
* **Strict Logical Isolation:** Implements a single-entry validation loop (`while (!isValidStudent)`). The application will not release system memory or advance until every single boundary criteria is satisfied.
* **Crash-Proof Exception Handling:** Wraps the explicit conversion of the student ID string into a mathematical integer inside a structured `try-catch` block. This gracefully intercepts `NumberFormatException` bugs if a user attempts to inject alphabetic characters or symbols into the ID field.
* **Institutional Boundary Controls:** Enforces a rigid length constraint (`tempNo.length() == 9`). This mirrors TUT's actual database structure, blocking truncated entries or numeric overflows.

## 🛠️ Tech Stack & Methods Used
* **Language:** Java SE
* **Input Streaming:** `java.util.Scanner`
* **Core Logic:** `String.substring()`, `String.indexOf()`, `String.contains()`, `Integer.parseInt()`
* **Architecture:** Structured Exception Handling (SEH) & Conditional Loop Control

## 🚀 How to Run and Test
1. Clone this repository or copy the `StudentValidation.java` file.
2. Compile the file using your terminal or IDE:
   ```bash
   javac StudentValidation.java
   ```
3. Run the application:
   ```bash
   java StudentValidation
   ```

### 🧪 Test Scenarios Handled Successfully:
* **Valid Input:** `224123456@tut4life.ac.za` ➡️ **Access Granted**
* **Wrong Domain:** `224123456@gmail.com` ➡️ **Error: Invalid domain**
* **Malformed Data types:** `abc123xyz@tut4life.ac.za` ➡️ **Error: Contains invalid letters or symbols**
* **Invalid Length:** `123@tut4life.ac.za` ➡️ **Error: Must be exactly 9 digits long**
