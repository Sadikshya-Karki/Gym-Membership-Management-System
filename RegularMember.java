/**
 * Class of a Regular Member of the gym.
 * Extends the abstract class GymMember and contains additional attributes and methods.
 * 
 * @author Sadikshya Karki
 */
public class RegularMember extends GymMember {
    // Regular membership attributes
    private final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    /**
     * Constructor to initialize regular member details.
     *
     * @param id                        ID of the gym member
     * @param name                      Name of the gym member
     * @param location                  Location of the gym member
     * @param phone                     Phone number of the gym member
     * @param email                     Email address of the gym member
     * @param gender                    Gender of the gym member
     * @param DOB                       Date of birth of the gym member  
     * @param membershipStartDate       Membership start date of the gym member
     * @param referralSource Source     From which the gym member was referred
     */
    public RegularMember(int id, String name, String location, String phone, String email,
    String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    // Accessor (Getter) methods to retrieve attribute values
    public int getAttendanceLimit() { 
        return attendanceLimit; 
    }
    public boolean getIsEligibleForUpgrade() { 
        return isEligibleForUpgrade;
    }
    public String getRemovalReason() { 
        return removalReason;
    }
    public String getReferralSource() { 
        return referralSource;
    }
    public String getPlan() { 
        return plan;
    }
    public double getPrice() { 
        return price;
    }

    /**
     * Overrides markAttendance() to increment attendance and loyalty points.
     * Checks and updates eligibility for an upgrade.
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }
    }

     /**
     * Method to get the price of a plan based on its name.
     *
     * @param plan      Plan name (basic, standard, deluxe)
     * @return Price of the plan(if valid) or -1 (if invalid)
     */
    public double getPlanPrice(String plan) {
        switch(plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }

    /**
     * Upgrades the current plan if eligible.
     *
     * @param newPlan    New plan to upgrade to
     * @return Result message of the upgrade process
     */
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade. Need more attendance.";
        }
        
        if (newPlan.toLowerCase().equals(this.plan)) {
            return "Already subscribed to this plan.";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }

        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan upgraded successfully to " + newPlan;
    }

    /**
     * Resets regular member details and sets a removal reason.
     *
     * @param removalReason      Reason for reverting the regular member
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    /**
     * Overrides display() to show regular member details.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}