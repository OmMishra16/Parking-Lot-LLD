import java.time.LocalDateTime;

public class Payment {
    private double amount;
    private LocalDateTime paymentTime;
    private PaymentMethod paymentMethod;
    private String ticketId;
    
    public Payment(double amount, PaymentMethod paymentMethod, String ticketId) {
        this.amount = amount;
        this.paymentTime = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
        this.ticketId = ticketId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public String getTicketId() {
        return ticketId;
    }
}