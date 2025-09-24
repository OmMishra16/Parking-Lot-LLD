import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

public class ParkingLot {
    private static ParkingLot instance;
    private List<Floor> floors;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;
    private ParkingStrategy parkingStrategy;
    private PricingStrategy pricingStrategy;
    private Map<String, Ticket> activeTickets;
    
    private ParkingLot() {
        this.floors = new ArrayList<>();
        this.entryGates = new ArrayList<>();
        this.exitGates = new ArrayList<>();
        this.parkingStrategy = new NearestSlotStrategy();
        this.pricingStrategy = new HourlyPricingStrategy();
        this.activeTickets = new HashMap<>();
    }
    
    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }
    
    public Ticket parkVehicle(Vehicle vehicle, String entryGateId) {
        if (isFull()) {
            System.out.println("Parking lot is full. Entry denied for vehicle: " + vehicle.getVehicleNumber());
            return null;
        }
        
        ParkingSlot availableSlot = parkingStrategy.findSlot(vehicle, floors);
        if (availableSlot == null) {
            System.out.println("No suitable slot found for vehicle: " + vehicle.getVehicleNumber());
            return null;
        }
        
        if (availableSlot.parkVehicle(vehicle)) {
            EntryGate entryGate = findEntryGate(entryGateId);
            if (entryGate != null) {
                Ticket ticket = entryGate.issueTicket(vehicle, availableSlot);
                activeTickets.put(ticket.getTicketId(), ticket);
                System.out.println("Vehicle " + vehicle.getVehicleNumber() + 
                                 " parked at slot " + availableSlot.getSlotId() + 
                                 " on floor " + availableSlot.getFloorNumber());
                return ticket;
            }
        }
        
        return null;
    }
    
    public Payment exitVehicle(String ticketId, String exitGateId, PaymentMethod paymentMethod) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket ID: " + ticketId);
            return null;
        }
        
        ParkingSlot slot = findSlotById(ticket.getSlotId(), ticket.getFloorNumber());
        if (slot == null) {
            System.out.println("Slot not found for ticket: " + ticketId);
            return null;
        }
        
        Vehicle vehicle = slot.removeVehicle();
        if (vehicle == null) {
            System.out.println("No vehicle found in slot: " + slot.getSlotId());
            return null;
        }
        
        LocalDateTime exitTime = LocalDateTime.now();
        double amount = pricingStrategy.calculatePrice(ticket, exitTime, slot);
        
        ExitGate exitGate = findExitGate(exitGateId);
        if (exitGate != null) {
            Payment payment = exitGate.processPayment(ticket, amount, paymentMethod);
            activeTickets.remove(ticketId);
            
            System.out.println("Vehicle " + vehicle.getVehicleNumber() + 
                             " exited. Amount paid: $" + String.format("%.2f", amount));
            return payment;
        }
        
        return null;
    }
    
    public void addFloor(Floor floor) {
        floors.add(floor);
    }
    
    public void addEntryGate(EntryGate gate) {
        entryGates.add(gate);
    }
    
    public void addExitGate(ExitGate gate) {
        exitGates.add(gate);
    }
    
    public boolean isFull() {
        for (Floor floor : floors) {
            if (!floor.isFull()) {
                return false;
            }
        }
        return true;
    }
    
    public int getTotalAvailableSlots() {
        int total = 0;
        for (Floor floor : floors) {
            total += floor.getAvailableSlots();
        }
        return total;
    }
    
    private EntryGate findEntryGate(String gateId) {
        for (EntryGate gate : entryGates) {
            if (gate.getGateId().equals(gateId)) {
                return gate;
            }
        }
        return null;
    }
    
    private ExitGate findExitGate(String gateId) {
        for (ExitGate gate : exitGates) {
            if (gate.getGateId().equals(gateId)) {
                return gate;
            }
        }
        return null;
    }
    
    private ParkingSlot findSlotById(String slotId, int floorNumber) {
        for (Floor floor : floors) {
            if (floor.getFloorNumber() == floorNumber) {
                return floor.findSlotById(slotId);
            }
        }
        return null;
    }
    
    public void setParkingStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }
    
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }
    
    public void displayStatus() {
        System.out.println("\n=== Parking Lot Status ===");
        System.out.println("Total Floors: " + floors.size());
        System.out.println("Total Available Slots: " + getTotalAvailableSlots());
        System.out.println("Active Tickets: " + activeTickets.size());
        
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + 
                             ": Available slots = " + floor.getAvailableSlots());
        }
        System.out.println("========================\n");
    }
}