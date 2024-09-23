package com.designpatterns.creational.factory;

import com.designpatterns.util.LoggerUtil;

interface Vehicle {
    void manufacture();
}

class Car implements Vehicle {
    @Override
    public void manufacture() {
        LoggerUtil.log("Manufacturing a car");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void manufacture() {
        LoggerUtil.log("Manufacturing a motorcycle");
    }
}

class Truck implements Vehicle {
    @Override
    public void manufacture() {
        LoggerUtil.log("Manufacturing a truck");
    }
}

class VehicleFactory {
    public Vehicle createVehicle(String vehicleType) {
        switch (vehicleType.toLowerCase()) {
            case "car":
                return new Car();
            case "motorcycle":
                return new Motorcycle();
            case "truck":
                return new Truck();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}

public class VehicleManufacturingDemo {
    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();

        Vehicle car = factory.createVehicle("car");
        car.manufacture();

        Vehicle motorcycle = factory.createVehicle("motorcycle");
        motorcycle.manufacture();

        Vehicle truck = factory.createVehicle("truck");
        truck.manufacture();
    }
}