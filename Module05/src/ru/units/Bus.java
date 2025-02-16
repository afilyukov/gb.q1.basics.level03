package ru.units;

import ru.GasStation;

import java.util.concurrent.Semaphore;

public class Bus extends Car {
    public Bus (Semaphore semaphore, String licencePlate, GasStation gasStation){
        this.setSemaphore(semaphore);
        this.setLicencePlate(licencePlate);
        this.setCurrentGas(this.getTankSize());
        this.setGasStation(gasStation);
        this.setConsumption(40f);
        this.setTankSize(7f);
        this.setType(TypeOfCar.BUS);
    }
}
