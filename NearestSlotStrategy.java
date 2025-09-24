import java.util.List;

public class NearestSlotStrategy implements ParkingStrategy {
    
    @Override
    public ParkingSlot findSlot(Vehicle vehicle, List<Floor> floors) {
        for (Floor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied() && vehicle.canFitInSlot(slot.getSlotType())) {
                    if (vehicle instanceof ElectricCar && ((ElectricCar) vehicle).needsCharging()) {
                        if (slot.getSlotType() == SlotType.E_CHARGE) {
                            return slot;
                        }
                    } else {
                        return slot;
                    }
                }
            }
        }
        
        for (Floor floor : floors) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied() && vehicle.canFitInSlot(slot.getSlotType())) {
                    return slot;
                }
            }
        }
        
        return null;
    }
}