public class VehicleFactory {
    
    public static Vehicle createVehicle(VehicleType type, String vehicleNumber) {
        switch (type) {
            case CAR:
                return new Car(vehicleNumber);
            case BIKE:
                return new Bike(vehicleNumber);
            case ELECTRIC_CAR:
                return new ElectricCar(vehicleNumber);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}