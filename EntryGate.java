import java.util.UUID;

public class EntryGate {
    private String gateId;
    
    public EntryGate(String gateId) {
        this.gateId = gateId;
    }
    
    public Ticket issueTicket(Vehicle vehicle, ParkingSlot assignedSlot) {
        String ticketId = generateTicketId();
        return new Ticket(ticketId, vehicle.getVehicleNumber(), gateId, 
                         assignedSlot.getSlotId(), assignedSlot.getFloorNumber());
    }
    
    private String generateTicketId() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8);
    }
    
    public String getGateId() {
        return gateId;
    }
}