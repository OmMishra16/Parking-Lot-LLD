public class SlotFactory {
    
    public static ParkingSlot createSlot(SlotType type, String slotId, int floorNumber) {
        switch (type) {
            case REGULAR:
                return new RegularSlot(slotId, floorNumber);
            case LARGE:
                return new LargeSlot(slotId, floorNumber);
            case E_CHARGE:
                return new EChargeSlot(slotId, floorNumber);
            default:
                throw new IllegalArgumentException("Invalid slot type: " + type);
        }
    }
}