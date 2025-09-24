import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private String vehicleNumber;
    private LocalDateTime entryTime;
    private String entryGate;
    private String slotId;
    private int floorNumber;
    
    public Ticket(String ticketId, String vehicleNumber, String entryGate, String slotId, int floorNumber) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = LocalDateTime.now();
        this.entryGate = entryGate;
        this.slotId = slotId;
        this.floorNumber = floorNumber;
    }
    
    public String getTicketId() {
        return ticketId;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    
    public String getEntryGate() {
        return entryGate;
    }
    
    public String getSlotId() {
        return slotId;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
}