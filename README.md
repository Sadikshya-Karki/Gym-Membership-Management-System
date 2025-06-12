Gym Membership Management System

This Java Swing-based application allows gym staff to manage gym members' information, membership status, and payments efficiently. It supports both **Regular** and **Premium** members and stores member details in a formatted text file.

Features

1. Save Member Details to File

- Saves all gym members’ details to `MemberDetails.txt`.
- Includes basic information for every member.
- Includes additional payment details for premium members (full payment status, discount, and net amount paid).
- Displays a success message upon successful saving or an error message if an issue occurs.

2. Read Member Details from File

- Reads member data from `MemberDetails.txt`.
- If the file is missing, prompts the user to select a file manually.
- Displays the member details in a new window with formatted, monospaced text.
- Handles errors gracefully and informs the user if reading fails.

3. Deactivate Membership

- Allows deactivation of a member’s membership by entering their ID.
- Validates the input and confirms success or shows an error if the member is not found or ID is invalid.

4. Pay Due Amount (Premium Members)

- Enables payment of outstanding dues for premium members.
- Validates member ID and payment amount input.
- Checks membership type and updates payment details accordingly.
- Provides feedback messages for success, invalid inputs, or if the member is not premium.

5. Clear Input Fields

- Resets all form fields and selections to their default empty state.
- Clears text inputs, combo boxes, and radio button selections.


How to Use

1. Run the Application
   Execute the `main` method in the `GymGUI` class to launch the interface.

2. Add or Manage Members 
   Input member details using the provided fields and use buttons to save, deactivate, or process payments.

3. Save to File
   Click the "Save" button (or trigger `saveToFile()` method) to export current member data to `MemberDetails.txt`.

4. Load from File  
   Use the "Load" button (or `readFromFile()` method) to open and display member details from the saved file or select another file if needed.

5. Deactivate Membership  
   Enter a member’s ID and deactivate their membership using the designated button.

6. Process Payment for Premium Members 
   Enter a premium member’s ID and the payment amount, then confirm the payment.

7. Clear Inputs 
   Use the "Clear" button (or `clearFields()` method) to reset all form inputs.


Notes

- The system distinguishes Regular and Premium members with different payment attributes.
- All file operations handle exceptions to prevent crashes and inform users of issues.
- The text file format uses aligned columns for easy human readability.
- This system requires Java Swing and standard I/O libraries.


Sample Output File Format (`MemberDetails.txt`)

```
ID    Name                 Location             Phone           Email                               Membership Start Date           Plan       Price      Attendance           Loyalty Points       Active Status       Full Payment         Discount Amount       Net Amount Paid    
1     Sadikshya Karki      Itahari              9800000000      sadikshyakarki111@gmail.com         2020-02-10                      regular    50.0       12                   30.00                Active              N/A                  N/A                   N/A                        
```
