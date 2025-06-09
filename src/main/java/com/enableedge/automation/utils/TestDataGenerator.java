package com.enableedge.automation.utils;

import java.util.Random;
import java.util.UUID;

public class TestDataGenerator {
    private static final Random random = new Random();

    public static String getRandomEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    public static String getRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String getRandomName() {
        String[] firstNames = {"John", "Jane", "Mike", "Sarah", "David", "Emily", "James", "Emma"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller"};
        
        return firstNames[random.nextInt(firstNames.length)] + " " + 
               lastNames[random.nextInt(lastNames.length)];
    }

    public static String getRandomAddress() {
        String[] streets = {"Main St", "Park Ave", "Oak St", "Maple St", "Elm St"};
        return (random.nextInt(9999) + 1) + " " + streets[random.nextInt(streets.length)];
    }

    public static String getRandomCity() {
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        return cities[random.nextInt(cities.length)];
    }

    public static String getRandomState() {
        String[] states = {"NY", "CA", "TX", "FL", "IL", "PA"};
        return states[random.nextInt(states.length)];
    }

    public static String getRandomZipcode() {
        return String.format("%05d", random.nextInt(99999));
    }

    public static String getRandomPhoneNumber() {
        return String.format("%d%d%d-%d%d%d-%d%d%d%d",
            random.nextInt(9) + 1, random.nextInt(9), random.nextInt(9),
            random.nextInt(9), random.nextInt(9), random.nextInt(9),
            random.nextInt(9), random.nextInt(9), random.nextInt(9), random.nextInt(9));
    }

    public static String getRandomProductName() {
        String[] adjectives = {"Classic", "Modern", "Premium", "Luxury", "Eco-Friendly"};
        String[] nouns = {"T-Shirt", "Jeans", "Shoes", "Dress", "Jacket"};
        
        return adjectives[random.nextInt(adjectives.length)] + " " + 
               nouns[random.nextInt(nouns.length)];
    }

    public static double getRandomPrice() {
        return Math.round((random.nextDouble() * (1000 - 10) + 10) * 100.0) / 100.0;
    }
}
