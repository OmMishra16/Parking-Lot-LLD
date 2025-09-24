public abstract class ParkingSlot {
    private String slotId;
    private SlotType slotType;
    private boolean isOccupied;
    private Vehicle vehicle;
    private int floorNumber;
    
    public ParkingSlot(String slotId, SlotType slotType, int floorNumber) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.floorNumber = floorNumber;
        this.isOccupied = false;
        this.vehicle = null;
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        if (isOccupied || !vehicle.canFitInSlot(slotType)) {
            return false;
        }
        
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }
    
    public Vehicle removeVehicle() {
        if (!isOccupied) {
            return null;
        }
        
        Vehicle removedVehicle = this.vehicle;
        this.vehicle = null;
        this.isOccupied = false;
        return removedVehicle;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public String getSlotId() {
        return slotId;
    }
    
    public SlotType getSlotType() {
        return slotType;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public abstract double getHourlyRate();
}