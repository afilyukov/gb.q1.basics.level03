package ru.units;

import ru.GasStation;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Car implements Machine, Runnable {
    private String licencePlate;
    private float consumption;

    private float tankSize;
    private float currentGas;
    private Semaphore semaphore;
    private GasStation gasStation;
    private TypeOfCar type;

    public Car(){
        this.semaphore = null;
        this.licencePlate = "";
        this.currentGas = 0;
        this.gasStation = null;
    }

    @Override
    public void go() {
            System.out.printf("%s[%s] started%n", this.type, this.licencePlate);
            while (true){
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.currentGas -= consumption;
                if (currentGas < consumption) {
                    System.out.printf("%s[%s] is going to refuel%n", this.type, this.licencePlate);
                    try {
                        semaphore.acquire();
                        if (gasStation.refuel(this)) {
                            currentGas = tankSize;
                            System.out.printf("%s[%s] refueled. Going futher%n", this.type, this.licencePlate);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
     }

    @Override
    public void stop(){
        System.out.printf("%s[%s] is stopping%n", licencePlate, consumption);
    }

    @Override
    public void run() {
        go();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Float.compare(car.consumption, consumption) == 0 && Float.compare(car.tankSize, tankSize) == 0 && Float.compare(car.currentGas, currentGas) == 0 && licencePlate.equals(car.licencePlate) && semaphore.equals(car.semaphore) && gasStation.equals(car.gasStation) && type == car.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, consumption, tankSize, currentGas, semaphore, gasStation, type);
    }

    public float getTankSize() {
        return tankSize;
    }

    public void setTankSize(float tankSize) {
        this.tankSize = tankSize;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public float getCurrentGas() {
        return currentGas;
    }

    public void setCurrentGas(float currentGas) {
        this.currentGas = currentGas;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public GasStation getGasStation() {
        return gasStation;
    }

    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
    }

    public TypeOfCar getType() {
        return type;
    }

    public void setType(TypeOfCar type) {
        this.type = type;
    }
}
