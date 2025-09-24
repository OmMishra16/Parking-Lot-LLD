import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculatePrice(Ticket ticket, LocalDateTime exitTime, ParkingSlot slot);
}