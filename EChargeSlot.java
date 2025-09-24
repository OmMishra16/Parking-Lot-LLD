public class EChargeSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 12.0;
    private static final double CHARGING_RATE = 5.0;
    
    public EChargeSlot(String slotId, int floorNumber) {
        super(slotId, SlotType.E_CHARGE, floorNumber);
    }
    
    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
    
    public double getChargingRate() {
        return CHARGING_RATE;
    }
    
    public boolean hasChargingCapability() {
        return true;
    }
}