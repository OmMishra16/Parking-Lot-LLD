import java.util.List;

public interface ParkingStrategy {
    ParkingSlot findSlot(Vehicle vehicle, List<Floor> floors);
}