/**
 * Class of a Premium Member of the gym.
 * Extends the abstract class GymMember and contains additional attributes and methods.
 * 
 * @author Sadikshya Karki
 */
public class PremiumMember extends GymMember {
    // Premium membership attributes
    private final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    /**
     * Constructor to initialize premium member details
     *
     * @param id                        ID of the gym member
     * @param name                      Name of the gym member
     * @param location                  Location of the gym member
     * @param phone                     Phone number of the gym member
     * @param email                     Email address of the gym member
     * @param gender                    Gender of the gym member
     * @param DOB                       Date of birth of the gym member
     * @param membershipStartDate       Membership start date of the gym member
     * @param personalTrainer           Personal trainer for the gym member
     */
    public PremiumMember(int id, String name, String location, String phone, String email,
    String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Accessor (Getter) methods to retrieve attribute values
    public double getPremiumCharge() { 
        return premiumCharge; 
    }
    public String getPersonalTrainer() { 
        return personalTrainer; 
    }
    public boolean getIsFullPayment() { 
        return isFullPayment; 
    }
    public double getPaidAmount() { 
        return paidAmount; 
    }
    public double getDiscountAmount() { 
        return discountAmount; 
    }

    /**
     * Marks attendance and updates loyalty points for the premium member.
     */
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
    }

    /**
     * Handles payment process for the premium membership.
     *
     * @param amount       Amount to be paid
     * @return Message showing payment status
     */
    public String payDueAmount(double amount) {
        if (this.isFullPayment) {
            return "Payment is already complete.";
        }

        if (this.paidAmount + amount > premiumCharge) {
            return "Payment amount exceeds the premium charge.";
        }

        this.paidAmount += amount;
        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
        }

        double remainingAmount = premiumCharge - this.paidAmount;
        return "Payment successful. Remaining amount: " + remainingAmount;
    }

    /**
     * Calculates discount if full payment is done.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = premiumCharge * 0.10;
            System.out.println("Discount calculated: " + discountAmount);
        } else {
            System.out.println("No discount available. Complete payment first.");
        }
    }

    /**
     * Resets premium member details.
     */
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    /**
     * Overrides display() to show premium member details.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}