# Parking Lot Management System

A comprehensive Low-Level Design (LLD) implementation of a parking lot management system in Java, demonstrating object-oriented design principles and design patterns.

## Table of Contents
- [Features](#features)
- [System Architecture](#system-architecture)
- [Design Patterns](#design-patterns)
- [Class Structure](#class-structure)
- [Getting Started](#getting-started)
- [Usage Examples](#usage-examples)
- [Extensibility](#extensibility)
- [Requirements Fulfilled](#requirements-fulfilled)

## Features

### Core Functionality
- ✅ **Multi-floor parking** with different slot types
- ✅ **Multiple entry/exit points** (all on ground floor)
- ✅ **Ticket generation** with entry time and slot allocation
- ✅ **Time-based payment calculation** with multiple strategies
- ✅ **Full parking lot detection** and entry denial
- ✅ **Multiple vehicle type support** (Car, Bike, Electric Car)
- ✅ **Electric vehicle charging slots** with premium pricing
- ✅ **Nearest available slot allocation**
- ✅ **Vehicle-slot compatibility** enforcement

### Advanced Features
- 🔄 **Strategy Pattern** for flexible slot allocation and pricing
- 🏭 **Factory Pattern** for vehicle and slot creation
- 🔒 **Singleton Pattern** for parking lot management
- 📊 **Real-time status tracking** and reporting
- 🔌 **Electric charging integration** with separate pricing
- 🚗 **Smart slot allocation** (large vehicles can't use small slots, small can use large)

## System Architecture

```
ParkingLot (Singleton)
├── Floors (Multiple)
│   └── ParkingSlots (Regular/Large/ECharge)
├── Entry/Exit Gates
├── Ticket Management
├── Payment Processing
└── Strategy Components
    ├── ParkingStrategy (Slot allocation)
    └── PricingStrategy (Payment calculation)
```

## Design Patterns

### 1. Strategy Pattern
**Used for:** Slot allocation and pricing strategies
```java
// Parking strategies
ParkingStrategy -> NearestSlotStrategy, CheapestSlotStrategy

// Pricing strategies  
PricingStrategy -> HourlyPricingStrategy, DynamicPricingStrategy
```
**Benefit:** Easy to add new allocation/pricing algorithms without changing core logic

### 2. Factory Pattern
**Used for:** Vehicle and slot object creation
```java
VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-1234")
SlotFactory.createSlot(SlotType.E_CHARGE, "G-E-001", 0)
```
**Benefit:** Centralized object creation with validation and consistency

### 3. Singleton Pattern  
**Used for:** Parking lot instance management
```java
ParkingLot parkingLot = ParkingLot.getInstance();
```
**Benefit:** Single point of control for the entire parking system

### 4. Template Method Pattern
**Used in:** Vehicle hierarchy for slot compatibility
```java
abstract class Vehicle {
    public abstract boolean canFitInSlot(SlotType slotType);
}
```

## Class Structure

### Core Entities
- **ParkingLot**: Main system controller (Singleton)
- **Floor**: Manages parking slots on each level
- **ParkingSlot**: Abstract base for slot types (Regular/Large/ECharge)
- **Vehicle**: Abstract base for vehicle types (Car/Bike/ElectricCar)
- **Ticket**: Parking receipt with entry details
- **Payment**: Transaction record

### Supporting Classes  
- **EntryGate/ExitGate**: Access point management
- **VehicleFactory/SlotFactory**: Object creation utilities
- **ParkingStrategy/PricingStrategy**: Pluggable algorithms

### Enums
- **VehicleType**: CAR, BIKE, ELECTRIC_CAR
- **SlotType**: REGULAR, LARGE, E_CHARGE  
- **PaymentMethod**: CASH, CARD, UPI, WALLET

## Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE or command line

### Installation
1. Clone or download the project files
2. Ensure all `.java` files are in the same directory
3. Compile the project:
```bash
javac *.java
```

### Running the Demo
```bash
java ParkingLotDemo
```

## Usage Examples

### Basic Parking Operations
```java
// Get parking lot instance
ParkingLot parkingLot = ParkingLot.getInstance();

// Create a vehicle
Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR, "KA-01-1234");

// Park vehicle
Ticket ticket = parkingLot.parkVehicle(car, "ENTRY-01");

// Exit vehicle with payment
Payment payment = parkingLot.exitVehicle(ticket.getTicketId(), "EXIT-01", PaymentMethod.CARD);
```

### Setting Up Parking Infrastructure
```java
// Create floors
Floor groundFloor = new Floor(0);
groundFloor.addSlot(SlotFactory.createSlot(SlotType.REGULAR, "G-R-001", 0));
groundFloor.addSlot(SlotFactory.createSlot(SlotType.E_CHARGE, "G-E-001", 0));

// Add to parking lot
parkingLot.addFloor(groundFloor);
parkingLot.addEntryGate(new EntryGate("ENTRY-01"));
parkingLot.addExitGate(new ExitGate("EXIT-01"));
```

### Changing Strategies
```java
// Switch to different parking strategy
parkingLot.setParkingStrategy(new CheapestSlotStrategy());

// Switch to different pricing strategy  
parkingLot.setPricingStrategy(new DynamicPricingStrategy());
```

## Extensibility

### Adding New Vehicle Types
```java
public class Truck extends Vehicle {
    public Truck(String vehicleNumber) {
        super(vehicleNumber, VehicleType.TRUCK);
    }
    
    @Override
    public boolean canFitInSlot(SlotType slotType) {
        return slotType == SlotType.LARGE;
    }
}
```

### Adding New Slot Types
```java
public class HandicapSlot extends ParkingSlot {
    public HandicapSlot(String slotId, int floorNumber) {
        super(slotId, SlotType.HANDICAP, floorNumber);
    }
    
    @Override
    public double getHourlyRate() {
        return 5.0; // Discounted rate
    }
}
```

### Adding New Pricing Strategies
```java
public class WeekendPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Ticket ticket, LocalDateTime exitTime, ParkingSlot slot) {
        // Implement weekend surge pricing
        return basePrice * 1.5;
    }
}
```

## Requirements Fulfilled

| Requirement | Status | Implementation |
|------------|---------|----------------|
| Multiple floors with parking slots | ✅ | `Floor` class with slot management |
| Multiple entry/exit points | ✅ | `EntryGate` and `ExitGate` classes |
| Ticket with entry time and slot | ✅ | `Ticket` class with all required fields |
| Time-based payment | ✅ | `HourlyPricingStrategy` |
| Full parking lot denial | ✅ | `isFull()` check before entry |
| Multiple vehicle types | ✅ | `Vehicle` hierarchy (Car/Bike/ElectricCar) |
| Electric charging slots | ✅ | `EChargeSlot` with premium pricing |
| Nearest available slot | ✅ | `NearestSlotStrategy` |
| Future extensibility | ✅ | Strategy pattern and factory pattern |
| Vehicle number, entry time, gate, slot | ✅ | Complete `Ticket` implementation |
| Dynamic pricing capability | ✅ | `PricingStrategy` interface |
| Size-based slot allocation | ✅ | `canFitInSlot()` method in vehicles |

## Project Structure
```
ParkingLot/
├── .gitignore
├── README.md
├── Vehicle.java (+ Car.java, Bike.java, ElectricCar.java)
├── ParkingSlot.java (+ RegularSlot.java, LargeSlot.java, EChargeSlot.java)  
├── ParkingLot.java (Main system)
├── Floor.java
├── Ticket.java
├── Payment.java
├── EntryGate.java, ExitGate.java
├── *Strategy.java (Strategy pattern implementations)
├── *Factory.java (Factory pattern implementations)
├── *Type.java (Enums)
└── ParkingLotDemo.java (Demo application)
```

## Demo Output
The demo application shows:
- Parking lot setup with 3 floors and different slot types
- Vehicle parking with automatic slot allocation
- Electric car getting charging slot preference
- Payment calculation with time-based pricing  
- Full parking lot scenario handling
- Real-time status updates
