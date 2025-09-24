public class ElectricCar extends Vehicle {
    
    public ElectricCar(String vehicleNumber) {
        super(vehicleNumber, VehicleType.ELECTRIC_CAR);
    }
    
    @Override
    public boolean canFitInSlot(SlotType slotType) {
        return slotType == SlotType.REGULAR || slotType == SlotType.LARGE || slotType == SlotType.E_CHARGE;
    }
    
    public boolean needsCharging() {
        return true;
    }
}