import java.time.LocalDateTime;
import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculatePrice(Ticket ticket, LocalDateTime exitTime, ParkingSlot slot) {
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = duration.toHours();
        if (duration.toMinutesPart() > 0) {
            hours++;
        }
        
        double basePrice = slot.getHourlyRate() * hours;
        
        if (slot instanceof EChargeSlot && slot.getVehicle() instanceof ElectricCar) {
            basePrice += ((EChargeSlot) slot).getChargingRate() * hours;
        }
        
        return basePrice;
    }
}