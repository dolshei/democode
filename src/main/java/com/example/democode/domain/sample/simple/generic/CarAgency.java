package com.example.democode.domain.sample.simple.generic;

public class CarAgency implements Rentable<Car> {

    @Override
    public Car rent() {
        return new Car();
    }
}
