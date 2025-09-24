public class ParkingLotDemo {
    
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        
        setupParkingLot(parkingLot);
        
        System.out.println("=== Parking Lot System Demo ===\n");
        
        parkingLot.displayStatus();
        
        testParkingScenarios(parkingLot);
    }
    
    private static void setupParkingLot(ParkingLot parkingLot) {
        Floor groundFloor = new Floor(0);
        Floor firstFloor = new Floor(1);
        Floor secondFloor = new Floor(2);
        
        groundFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "G-R-001", 0));
        groundFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "G-R-002", 0));
        groundFloor.addSlot(SlotFactory.createSlot(SlotType.LARGE, "G-L-001", 0));
        groundFloor.addSlot(SlotFactory.createSlot(SlotType.E_CHARGE, "G-E-001", 0));
        
        firstFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "F1-R-001", 1));
        firstFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "F1-R-002", 1));
        firstFloor.addSlot(SlotFactory.createSlot(SlotType.LARGE, "F1-L-001", 1));
        firstFloor.addSlot(SlotFactory.createSlot(SlotType.E_CHARGE, "F1-E-001", 1));
        
        secondFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "F2-R-001", 2));
        secondFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "F2-R-002", 2));
        secondFloor.addSlot(SlotFactory.createSlot(SlotType.LARGE, "F2-L-001", 2));
        
        parkingLot.addFloor(groundFloor);
        parkingLot.addFloor(firstFloor);
        parkingLot.addFloor(secondFloor);
        
        parkingLot.addEntryGate(new EntryGate("ENTRY-01"));
        parkingLot.addEntryGate(new EntryGate("ENTRY-02"));
        
        parkingLot.addExitGate(new ExitGate("EXIT-01"));
        parkingLot.addExitGate(new ExitGate("EXIT-02"));
        
        System.out.println("Parking lot setup completed!\n");
    }
    
    private static void testParkingScenarios(ParkingLot parkingLot) {
        System.out.println("=== Testing Parking Scenarios ===\n");
        
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-1234");
        Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.BIKE, "KA-02-5678");
        Vehicle eCar1 = VehicleFactory.createVehicle(VehicleType.ELECTRIC_CAR, "KA-03-9012");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "KA-04-3456");
        
        Ticket ticket1 = parkingLot.parkVehicle(car1, "ENTRY-01");
        parkingLot.displayStatus();
        
        Ticket ticket2 = parkingLot.parkVehicle(bike1, "ENTRY-02");
        parkingLot.displayStatus();
        
        Ticket ticket3 = parkingLot.parkVehicle(eCar1, "ENTRY-01");
        parkingLot.displayStatus();
        
        Ticket ticket4 = parkingLot.parkVehicle(car2, "ENTRY-02");
        parkingLot.displayStatus();
        
        simulateTimeDelay();
        
        System.out.println("=== Testing Exit Scenarios ===\n");
        
        if (ticket1 != null) {
            Payment payment1 = parkingLot.exitVehicle(ticket1.getTicketId(), "EXIT-01", PaymentMethod.CARD);
            if (payment1 != null) {
                System.out.println("Payment completed: $" + String.format("%.2f", payment1.getAmount()));
            }
        }
        
        parkingLot.displayStatus();
        
        if (ticket3 != null) {
            Payment payment3 = parkingLot.exitVehicle(ticket3.getTicketId(), "EXIT-02", PaymentMethod.UPI);
            if (payment3 != null) {
                System.out.println("Payment completed: $" + String.format("%.2f", payment3.getAmount()));
            }
        }
        
        parkingLot.displayStatus();
        
        System.out.println("=== Testing Full Parking Lot ===\n");
        
        for (int i = 5; i <= 15; i++) {
            Vehicle testCar = VehicleFactory.createVehicle(VehicleType.CAR, "KA-" + String.format("%02d", i) + "-TEST");
            Ticket testTicket = parkingLot.parkVehicle(testCar, "ENTRY-01");
            if (testTicket == null) {
                System.out.println("Parking lot full - cannot park more vehicles");
                break;
            }
        }
        
        parkingLot.displayStatus();
        
        System.out.println("=== Demo Completed ===");
    }
    
    private static void simulateTimeDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}