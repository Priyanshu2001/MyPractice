package com.prbansal.authenticationactivity.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

public class Sample implements Serializable {
    /*public int red, green, violet;
    public int digit1, digit2, digit3;*/
    public HashMap<String, Integer> colors = new HashMap<>();
    public HashMap<String, Integer> digits = new HashMap<>();
    public int noOfBids;


    public Sample() {
    }
}
