public class LargeSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 15.0;
    
    public LargeSlot(String slotId, int floorNumber) {
        super(slotId, SlotType.LARGE, floorNumber);
    }
    
    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
}