package org;

import org.cars.Car;
import org.shops.Grocery;

/**
 * By Szczepan Faber on 6/8/13
 */
public class Shopping {

    public static void main(String ... args) {
        Car car = new Car();
        Grocery grocery = new Grocery();

        car.driveTo("Grocery");
        grocery.isProductAvailable("apple");
    }
}
