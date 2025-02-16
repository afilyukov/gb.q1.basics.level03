package ru.units;

import ru.GasStation;

import java.util.concurrent.Semaphore;

public class Truck extends Car {

    public Truck (Semaphore semaphore, String licencePlate, GasStation gasStation){
        this.setSemaphore(semaphore);
        this.setLicencePlate(licencePlate);
        this.setCurrentGas(this.getTankSize());
        this.setGasStation(gasStation);
        this.setConsumption(15f);
        this.setTankSize(60f);
        this.setType(TypeOfCar.TRUCK);
    }
}
