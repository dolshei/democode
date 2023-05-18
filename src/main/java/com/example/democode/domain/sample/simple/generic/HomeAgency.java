package com.example.democode.domain.sample.simple.generic;

public class HomeAgency implements Rentable<Home> {

    @Override
    public Home rent() {
        return new Home();
    }
}
