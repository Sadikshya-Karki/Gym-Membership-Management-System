/**
 * Abstract class of a Gym Member containing attributes and methods.
 * 
 * @author Sadikshya Karki
 */
public abstract class GymMember {
    // Protected attributes to store member details
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    /**
     * Constructor to initialize gym member details.
     * 
     * @param id                    ID of the gym member
     * @param name                  Name of the gym member
     * @param location              Location of the gym member
     * @param phone                 Phone number of the gym member
     * @param email                 Email address of the gym member
     * @param gender                Gender of the gym member
     * @param DOB                   Date of birth of the gym member
     * @param membershipStartDate   Membership start date of the gym member
     */
    public GymMember(int id, String name, String location, String phone, String email, 
    String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0; 
        this.loyaltyPoints = 0; 
        this.activeStatus = false; 
    }

    // Accessor (Getter) methods to retrieve attribute values
    public int getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getLocation() { 
        return location;
    }
    public String getPhone() { 
        return phone;
    }
    public String getEmail() { 
        return email; 
    }
    public String getGender() { 
        return gender; 
    }
    public String getDOB() { 
        return DOB;
    }
    public String getMembershipStartDate() { 
        return membershipStartDate;
    }
    public int getAttendance() { 
        return attendance;
    }
    public double getLoyaltyPoints() { 
        return loyaltyPoints; 
    }
    public boolean getActiveStatus() { 
        return activeStatus; 
    }
    
    /**
     * Abstract method to be implemented by subclasses for marking attendance.
     */
    public abstract void markAttendance();

    /**
     * Method to activate the membership.
     */
    public void activateMembership() {
        this.activeStatus = true;
    }

    /**
     * Method to deactivate the membership (only if it is currently active).
     */
    public void deactivateMembership() {
        if (this.activeStatus) {
            this.activeStatus = false;
        }
    }

    /**
     * Method to reset member details (attendance, loyalty points, and active status).
     */
    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    /**
     * Method to display member details.
     */
    public void display() {
        System.out.println("Gym Member Details: ");
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + activeStatus);
    }
}