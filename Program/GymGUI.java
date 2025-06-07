import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Graphical User Interface(GUI) for managing gym members.
 * 
 * It allows the input and display of the information of both regular and premium members.
 * 
 * @author Sadikshya Karki
 */
public class GymGUI extends JFrame {
    /**
     * List to store gym members
     */
    private ArrayList<GymMember> members;
    
    // Text Fields
    private JTextField idField, nameField, locationField, phoneField, emailField;
    private JTextField referralField, paidAmountField, removalReasonField, trainerField;
    
    // Read-only Fields
    private JTextField planPriceField, premiumChargeField, discountField;
    
    // Date Combo Boxes
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;
    
    // Radio Buttons
    private JRadioButton maleButton, femaleButton;
    
    // Plan Combo Box
    private JComboBox<String> planComboBox;
    
    /**
     * Constructor to initialize the GymGUI frame.
     * 
     * It sets up the title, default close operation, UI components and location of the frame.
     */
    public GymGUI() {
        members = new ArrayList<>();
        setTitle("S-Gym & Fitness");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupUI();
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * It sets up the combo boxes for selecting a date(year, month and day) for two fields,
     * Date of Birth(dob) and Membership Start Date(ms).
     * 
     * This method initializes the combo boxes for:
     * Years: From 1950 to 2024
     * Months: January to December
     * Days: 1 to 31
     */
    private void setupDateComboBoxes() {
        // Years (1950-2024)
        String[] years = new String[75];
        for (int i = 0; i < 75; i++) {
            years[i] = String.valueOf(1950 + i);
        }
        
        // Months
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        
        // Days (1-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        
        // Initializing combo boxes
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);
        
        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
    }
    
    /**
     * Sets up the user interface components.
     * This method initializes and sets the following sections:
     * 
     * 1. Personal Information Section:
     *    Member ID, Name, Location, Phone, Email, Gender, Date of Birth(dob)
     *    
     * 2. Membership Details Section:
     *    Membership Start Date, Referall Source, Plan, Regular Plan Price, 
     *    Premium Plan Charge, Paid Amount, Discount Amount, Trainer's Name, Removal Reason
     *    
     * 3. Action Buttons Sections:
     *    Action Buttons for adding regular and premium members, marking attendance, 
     *    reverting members, upgrading plans, activating and deactivating membership, 
     *    calculating discounts, paying due amounts, clearing all fields
     *    
     * It also sets up combo boxes for selecting date of birth and membership start dates,
     * adds appropriate action listeners to the buttons and confirm the layout and styling.
     */
    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Main container
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        mainPanel.setBackground(new Color(237, 210, 249));
        
        // Personal Information Panel
        JPanel personalInfoPanel = new JPanel(new GridLayout(0, 2, 15, 7));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
        "Personal Information"));
        personalInfoPanel.setBackground(new Color(253, 238, 251));
        
        personalInfoPanel.add(new JLabel("Member ID:"));
        idField = new JTextField(10);
        personalInfoPanel.add(idField);
        
        personalInfoPanel.add(new JLabel("Name:"));
        nameField = new JTextField(25);
        personalInfoPanel.add(nameField);
        
        personalInfoPanel.add(new JLabel("Location:"));
        locationField = new JTextField(25);
        personalInfoPanel.add(locationField);
        
        personalInfoPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(15);
        personalInfoPanel.add(phoneField);
        
        personalInfoPanel.add(new JLabel("Email:"));
        emailField = new JTextField(35);
        personalInfoPanel.add(emailField);
        
        // Gender Panel
        personalInfoPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(new Color(253, 238, 251));
        
        ButtonGroup genderGroup = new ButtonGroup();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        personalInfoPanel.add(genderPanel);
        
        // Setup Date Combo Boxes
        setupDateComboBoxes();
        
        // DOB Panel
        personalInfoPanel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.setBackground(new Color(253, 238, 251));
        
        dobDayComboBox.setBackground(Color.WHITE);
        dobMonthComboBox.setBackground(Color.WHITE);
        dobYearComboBox.setBackground(Color.WHITE);
        
        dobPanel.add(dobDayComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobYearComboBox);
        personalInfoPanel.add(dobPanel);
        
        
        // Membership Details Panel
        JPanel membershipPanel = new JPanel(new GridLayout(0, 2, 15, 7));
        membershipPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1), 
        "Membership Details"));
        membershipPanel.setBackground(new Color(253, 238, 251));
        
        // Membership Start Date Panel
        membershipPanel.add(new JLabel("Membership Start Date:"));
        JPanel msPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        msPanel.setBackground(new Color(253, 238, 251));
        
        msDayComboBox.setBackground(Color.WHITE);
        msMonthComboBox.setBackground(Color.WHITE);
        msYearComboBox.setBackground(Color.WHITE);
        
        msPanel.add(msDayComboBox);
        msPanel.add(msMonthComboBox);
        msPanel.add(msYearComboBox);
        membershipPanel.add(msPanel);
        
        membershipPanel.add(new JLabel("Referral Source:"));
        referralField = new JTextField(25);
        membershipPanel.add(referralField);
        
        membershipPanel.add(new JLabel("Plan:"));
        planComboBox = new JComboBox<>(new String[] { "Basic", "Standard", "Deluxe" });
        planComboBox.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String selectedPlan = (String) planComboBox.getSelectedItem();
            switch(selectedPlan.toLowerCase()) {
                case "basic":
                    planPriceField.setText("6500");
                    break;
                case "standard":
                    planPriceField.setText("12500");
                    break;
                case "deluxe":
                    planPriceField.setText("18500");
                    break;
                }
            }
        });
        
        planComboBox.setBackground(Color.WHITE);
        membershipPanel.add(planComboBox);

        membershipPanel.add(new JLabel("Regular Plan Price:"));
        planPriceField = new JTextField("6500");
        planPriceField.setEditable(false);
        membershipPanel.add(planPriceField);
        
        membershipPanel.add(new JLabel("Premium Plan Charge:"));
        premiumChargeField = new JTextField("50000");
        premiumChargeField.setEditable(false);
        membershipPanel.add(premiumChargeField);
        
        membershipPanel.add(new JLabel("Paid Amount:"));
        paidAmountField = new JTextField(15);
        membershipPanel.add(paidAmountField);
        
        membershipPanel.add(new JLabel("Discount Amount:"));
        discountField = new JTextField("0");
        discountField.setEditable(false);
        membershipPanel.add(discountField);
        
        membershipPanel.add(new JLabel("Trainer's Name:"));
        trainerField = new JTextField(25);
        membershipPanel.add(trainerField);
        
        membershipPanel.add(new JLabel("Removal Reason:"));
        removalReasonField = new JTextField(25);
        membershipPanel.add(removalReasonField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 15, 7));
        buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
        "Actions"));
        buttonPanel.setBackground(new Color(253, 238, 251));
        
        JButton addRegularButton = new JButton("Add Regular Member");
        JButton markAttendanceButton = new JButton("Mark Attendance");
        JButton revertMemberButton = new JButton("Revert Member");
        JButton addPremiumButton = new JButton("Add Premium Member");
        JButton upgradePlanButton = new JButton("Upgrade Plan");
        JButton displayButton = new JButton("Display Members");
        JButton activateButton = new JButton("Activate Membership");
        JButton calculateDiscountButton = new JButton("Calculate Discount");
        JButton saveToFileButton = new JButton("Save to File");
        JButton deactivateButton = new JButton("Deactivate Membership");
        JButton payDueButton = new JButton("Pay Due Amount");
        JButton readFromFileButton = new JButton("Read from File");
        JButton clearButton = new JButton("Clear");
        
        buttonPanel.add(addRegularButton);
        buttonPanel.add(markAttendanceButton);
        buttonPanel.add(revertMemberButton);
        buttonPanel.add(addPremiumButton);
        buttonPanel.add(upgradePlanButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(activateButton);
        buttonPanel.add(calculateDiscountButton);
        buttonPanel.add(saveToFileButton);
        buttonPanel.add(deactivateButton);
        buttonPanel.add(payDueButton);
        buttonPanel.add(readFromFileButton);
        buttonPanel.add(clearButton);
        
        // Adding all panels to main panel
        mainPanel.add(personalInfoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(membershipPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);
        
        // Adding main panel to frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        // Setting the frame size
        setPreferredSize(new Dimension(1200, 800));
        
        // Adding action listeners
        addRegularButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addRegularMember();
            }
         
        });
        
        markAttendanceButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                markAttendance();
            }
         
        });
        
        revertMemberButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                revertMember();
            }
         
        });
        
        addPremiumButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addPremiumMember();
            }
         
        });
        
        upgradePlanButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                upgradePlan();
            }
         
        });
        
        displayButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayMembers();
            }
         
        });
        
        activateButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                activateMembership();
            }
         
        });
        
        calculateDiscountButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                calculateDiscount();
            }
         
        });
        
        saveToFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                saveToFile();
            }
        });
        
        deactivateButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                deactivateMembership();
            }
        
        });
        
        payDueButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                payDueAmount();
            }
         
        });
        
        readFromFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                readFromFile();
            }
        });
        
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearFields();
            }
         
        });
    }
    
    /**
     * It checks if a member ID already exists in the member list.
     * It returns true if the ID is duplicate otherwise false.
     */
    private boolean isIdDuplicate(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the email is valid:
     * - No spaces
     * - Exactly one '@', not at start or end
     * - At least one '.' after '@', not at end
     * @return true if valid, false otherwise
     */
    private boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        if (email.contains(" ")) {
            return false;
        }
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');

        if (atIndex <= 0 || atIndex != lastAtIndex || atIndex == email.length() - 1) {
            return false;
        }

        int dotAfterAt = email.indexOf('.', atIndex);
        if (dotAfterAt == -1 || dotAfterAt == email.length() - 1) {
            return false;
        }
        
        return true;
    }
        
    /**
     * This method adds a new regular member to the member list after checking the details provided.
     * It makes sure the member ID is unique and all the required fields are filled.
     * It also displays an error message if the ID is invalid or the input format is incorrect.
     */
    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
            
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "/" + 
                        dobMonthComboBox.getSelectedItem() + "/" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "/" + 
                             msMonthComboBox.getSelectedItem() + "/" + 
                             msYearComboBox.getSelectedItem();
            String referral = referralField.getText();
            
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields!");
                return;
            }
            
            if (!isEmailValid(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address!");
                return;
            }

            RegularMember member = new RegularMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, referral);                                      
            members.add(member);
            JOptionPane.showMessageDialog(this, "Regular Member added successfully!");
            clearFields();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }

    /**
     * Marks a gym member attendance if their ID is valid and membership is active.
     * Shows success or error message accordingly.
     */
    private void markAttendance() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member.getActiveStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(this, "Attendance marked successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Membership is not active!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Reverts member status based on their ID is valid.
     * For Regular Members, a removal reason must be provided.
     * For Premium Members, no removal reason is required.
     * Shows an error if the ID is invalid or the member is not found.
     */
    private void revertMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String reason = removalReasonField.getText();
            
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        ((PremiumMember) member).revertPremiumMember();
                        JOptionPane.showMessageDialog(this, "Premium Member reverted successfully!");
                    } else if (member instanceof RegularMember) {
                        if (reason.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Please enter a removal reason!");
                            return;
                        }
                        ((RegularMember) member).revertRegularMember(reason);
                        JOptionPane.showMessageDialog(this, "Regular Member reverted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Unknown member type!");
                    }
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * This method adds a new premium member to the member list after checking the details provided.
     * It makes sure the member ID is unique and all the required fields are filled.
     * It also displays an error message if the ID is invalid or the input format is incorrect.
     */
    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
            
            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "/" + 
                        dobMonthComboBox.getSelectedItem() + "/" + 
                        dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "/" + 
                             msMonthComboBox.getSelectedItem() + "/" + 
                             msYearComboBox.getSelectedItem();
            String trainer = trainerField.getText();
            
            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields!");
                return;
            }
            
            if (!isEmailValid(email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address!");
                return;
            }
            
            PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                                                   gender, dob, startDate, trainer);
            members.add(member);
            JOptionPane.showMessageDialog(this, "Premium Member added successfully!");
            clearFields();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Upgrades regular member plan if their ID is valid, membership is active and they are a regular member,
     * otherwise shows an appropriate message.
     */
    private void upgradePlan() {
        try {
            int id = Integer.parseInt(idField.getText());
            String selectedPlan = (String) planComboBox.getSelectedItem();
            
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof RegularMember) {
                        RegularMember regularMember = (RegularMember) member;
                        if (member.getActiveStatus()) {
                            String result = regularMember.upgradePlan(selectedPlan);
                            JOptionPane.showMessageDialog(this, result);
                        } else {
                            JOptionPane.showMessageDialog(this, "Membership is not active!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Regular Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Displays the details of all members.
     * Lists each member's information including ID, name, location, phone, email, gender, DOB, membership start date,
     * attendance, loyalty points and active status.
     * For Regular Members, it shows the plan, price and removal reason if available.
     * For Premium Members, it shows personal trainer, paid amount, payment status, remaining amount and discount if applicable.
     * If no members are found, a message "No members found!" is displayed.
     */
    private void displayMembers() {
        JFrame displayFrame = new JFrame("Member Details");
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        
        if (members.isEmpty()) {
            textArea.setText("No members found!");
        } else {
            StringBuilder displayText = new StringBuilder();
            for (GymMember member : members) {
                if (member instanceof RegularMember) {
                    displayText.append("--- REGULAR MEMBER ---\n");
                } else if (member instanceof PremiumMember) {
                    displayText.append("--- PREMIUM MEMBER ---\n");
                }
                
                displayText.append("ID: ").append(member.getId()).append("\n");
                displayText.append("Name: ").append(member.getName()).append("\n");
                displayText.append("Location: ").append(member.getLocation()).append("\n");
                displayText.append("Phone: ").append(member.getPhone()).append("\n");
                displayText.append("Email: ").append(member.getEmail()).append("\n");
                displayText.append("Gender: ").append(member.getGender()).append("\n");
                displayText.append("DOB: ").append(member.getDOB()).append("\n");
                displayText.append("Start Date: ").append(member.getMembershipStartDate()).append("\n");
                displayText.append("Attendance: ").append(member.getAttendance()).append("\n");
                displayText.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                displayText.append("Active Status: ").append(member.getActiveStatus()).append("\n");
                
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    displayText.append("Plan: ").append(regMember.getPlan()).append("\n");
                    displayText.append("Price: ").append(regMember.getPrice()).append("\n");
                    if (!regMember.getRemovalReason().isEmpty()) {
                        displayText.append("Removal Reason: ").append(regMember.getRemovalReason()).append("\n");
                    }
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    displayText.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
                    displayText.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
                    displayText.append("Payment Status: ").append(premMember.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");
                    double remainingAmount = premMember.getPremiumCharge() - premMember.getPaidAmount();
                    displayText.append("Remaining Amount: ").append(remainingAmount).append("\n");
                    if (premMember.getIsFullPayment()) {
                        displayText.append("Discount Amount: ").append(premMember.getDiscountAmount()).append("\n");
                    }
                }
                
                displayText.append("\n---------------------------\n\n");
            }
            textArea.setText(displayText.toString());
        }
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        displayFrame.add(scrollPane);
        displayFrame.pack();
        displayFrame.setLocationRelativeTo(this);
        displayFrame.setVisible(true);
    }
    
    /**
     * Activates the membership of a gym member if the ID is valid and member is found in the list and shows success message,
     * otherwise shows an error message.
     */
    private void activateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.activateMembership();
                    JOptionPane.showMessageDialog(this, "Membership activated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Calculates and displays the discount for a premium member if their ID is valid,
     * otherwise shows an error message.
     */
    private void calculateDiscount() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.calculateDiscount();
                        discountField.setText(String.valueOf(premiumMember.getDiscountAmount()));
                        JOptionPane.showMessageDialog(this, "Discount calculated!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /**
     * Saves all gym members' details into a file named "MemberDetails.txt".
     * It includes basic info for every member and extra payment details for premium members.
     * Shows a success message when done or an error message if something goes wrong.
     * 
     */
    private void saveToFile() {
        try {
            File file = new File("MemberDetails.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            
            writer.printf("%-5s %-20s %-20s %-15s %-35s %-30s %-10s %-10s %-20s %-20s %-20s %-20s %-20s %-20s\n", 
                         "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price", 
                         "Attendance", "Loyalty Points", "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid");
            
            for (GymMember member : members) {
                String plan = "";
                double price = 0.0;
                String fullPayment = "N/A";
                String discountAmount = "N/A";
                String netAmountPaid = "N/A";
                
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    plan = regMember.getPlan();
                    price = regMember.getPrice();
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    plan = "premium";
                    price = premMember.getPremiumCharge();
                    fullPayment = premMember.getIsFullPayment() ? "Yes" : "No";
                    discountAmount = String.valueOf(premMember.getDiscountAmount());
                    netAmountPaid = String.valueOf(premMember.getPaidAmount());
                }
                
                writer.printf("%-5s %-20s %-20s %-15s %-35s %-30s %-10s %-10.1f %-20d %-20.2f %-20s %-20s %-20s %-20s\n",
                             member.getId(), member.getName(), member.getLocation(), member.getPhone(),
                             member.getEmail(), member.getMembershipStartDate(), plan, price,
                             member.getAttendance(), member.getLoyaltyPoints(),
                             member.getActiveStatus() ? "Active" : "Inactive", fullPayment, discountAmount, netAmountPaid);
            }
            
            writer.close();
            JOptionPane.showMessageDialog(this, "Saved to MemberDetails.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    
    /**
     * Deactivates the membership of a gym member if the ID is valid and member is found in the list and shows success message,
     * otherwise shows an error message.
     */
    private void deactivateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.deactivateMembership();
                    JOptionPane.showMessageDialog(this, "Membership deactivated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    /** 
     * Pays the due amount for a premium member if their ID is valid and payment amount is provided,
     * otherwise shows an error message.
     */
     private void payDueAmount() {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(paidAmountField.getText());
            
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        String result = premiumMember.payDueAmount(amount);
                        JOptionPane.showMessageDialog(this, result);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }
    
    /**
     * Reads member details from "MemberDetails.txt" and displays them in a new window.
     * If the file doesnot exist, it lets the user choose a file manually.
     * Shows an error message if the file cannot be read.
     */
    private void readFromFile() {
        try {
            File file = new File("MemberDetails.txt");
            
            if (!file.exists()) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open Member Details File");
                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                } else {
                    return; 
                }
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            // Creates a new frame for displaying member details
            JFrame displayFrame = new JFrame("Member Details");
            displayFrame.setSize(1000, 500);
            
            // Creates a text area to display the file content
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
            JScrollPane scrollPane = new JScrollPane(textArea);
            
            // Reads the entire file into the text area
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            
            displayFrame.add(scrollPane);
            displayFrame.setLocationRelativeTo(this);
            displayFrame.setVisible(true);
            
            reader.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
        }
    }
    
    /**
     * Clear all input fields and resets selections to their default state.
     */
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        planComboBox.setSelectedIndex(0);
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedIndex(0);
        msDayComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.clearSelection();
    }

    /**
     * Activates the GymGUI application.
     */
    public static void main(String[] args) {
        GymGUI gui = new GymGUI();
        gui.setVisible(true);
    }
}