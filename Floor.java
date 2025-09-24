import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Floor {
    private int floorNumber;
    private List<ParkingSlot> slots;
    private Map<SlotType, Integer> capacity;
    private Map<SlotType, Integer> occupiedCount;
    
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
        this.capacity = new HashMap<>();
        this.occupiedCount = new HashMap<>();
        
        for (SlotType type : SlotType.values()) {
            capacity.put(type, 0);
            occupiedCount.put(type, 0);
        }
    }
    
    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
        capacity.put(slot.getSlotType(), capacity.get(slot.getSlotType()) + 1);
    }
    
    public boolean removeSlot(String slotId) {
        ParkingSlot slotToRemove = null;
        for (ParkingSlot slot : slots) {
            if (slot.getSlotId().equals(slotId)) {
                if (slot.isOccupied()) {
                    return false;
                }
                slotToRemove = slot;
                break;
            }
        }
        
        if (slotToRemove != null) {
            slots.remove(slotToRemove);
            capacity.put(slotToRemove.getSlotType(), capacity.get(slotToRemove.getSlotType()) - 1);
            return true;
        }
        return false;
    }
    
    public ParkingSlot findSlotById(String slotId) {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotId().equals(slotId)) {
                return slot;
            }
        }
        return null;
    }
    
    public boolean isFull() {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                return false;
            }
        }
        return true;
    }
    
    public int getAvailableSlots() {
        int available = 0;
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                available++;
            }
        }
        return available;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public List<ParkingSlot> getSlots() {
        return new ArrayList<>(slots);
    }
    
    public Map<SlotType, Integer> getCapacity() {
        return new HashMap<>(capacity);
    }
}