public class Bike extends Vehicle {
    
    public Bike(String vehicleNumber) {
        super(vehicleNumber, VehicleType.BIKE);
    }
    
    @Override
    public boolean canFitInSlot(SlotType slotType) {
        return true;
    }
}