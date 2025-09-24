import java.time.LocalDateTime;

public class ExitGate {
    private String gateId;
    
    public ExitGate(String gateId) {
        this.gateId = gateId;
    }
    
    public Payment processPayment(Ticket ticket, double amount, PaymentMethod paymentMethod) {
        return new Payment(amount, paymentMethod, ticket.getTicketId());
    }
    
    public String getGateId() {
        return gateId;
    }
}