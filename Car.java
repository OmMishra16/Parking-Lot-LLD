public class Car extends Vehicle {
    
    public Car(String vehicleNumber) {
        super(vehicleNumber, VehicleType.CAR);
    }
    
    @Override
    public boolean canFitInSlot(SlotType slotType) {
        return slotType == SlotType.REGULAR || slotType == SlotType.LARGE;
    }
}