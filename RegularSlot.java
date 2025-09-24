public class RegularSlot extends ParkingSlot {
    private static final double HOURLY_RATE = 10.0;
    
    public RegularSlot(String slotId, int floorNumber) {
        super(slotId, SlotType.REGULAR, floorNumber);
    }
    
    @Override
    public double getHourlyRate() {
        return HOURLY_RATE;
    }
}