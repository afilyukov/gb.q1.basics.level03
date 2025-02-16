package ru;

import ru.units.Car;

import java.util.concurrent.Semaphore;

public class GasStation  {

    private final Semaphore semaphore;

    public GasStation(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public boolean refuel(Car car) {
          try {
              System.out.println("GASSTATION: " + car.getType() + " [" + car.getLicencePlate() + "] refueling");
              Thread.sleep(5000);
              semaphore.release();
              System.out.println("GASSTATION: " + car.getType() + " " + car.getLicencePlate() + " refueled");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          return true;
    }
}
