package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.Map;


public class RandomUtils {
    private static final Random random = new Random();
    Faker faker = new Faker();
    public static String randomState = getRandomState();

    public static String getRandomString(int len) {
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuv";
        SecureRandom rnd = new SecureRandom();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++)
            result.append(alphanumericCharacters.charAt(rnd.nextInt(alphanumericCharacters.length())));

        return result.toString();

    }

    public static String getRandomEmail() {
        return getRandomString(5) + "@mail.ru";

    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


//    public static String getRandomPhone() {
//        return String.format("+%s (%s) %s - %s - %s", getRandomInt(1, 9), getRandomInt(111, 999),
//                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
//    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return new Faker().options().option(genders);
    }


    public static String getRandomMonth() {
        String[] monthNames = {"January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        return new Faker().options().option(monthNames);

    }


    public static String getRandomYear() {
        Random random = new Random();
        int minYear = 1950;
        int maxYear = 2024;
        int randomYearInt = random.nextInt(maxYear - minYear + 1) + minYear;
        String randomYear = String.valueOf(randomYearInt);
        return randomYear;
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Arts", "English",
                "Biology", "Hindi", "Commerce"};
        return new Faker().options().option(subjects);

    }

    public static String getRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return new Faker().options().option(hobbies);
    }


    private static final Map<String, String[]> citiesByState = new HashMap<>();

    static {
        citiesByState.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        citiesByState.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        citiesByState.put("Haryana", new String[]{"Karnal", "Panipat"});
        citiesByState.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    private static String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return new Faker().options().option(states);
    }

    public static String getRandomCity() {
        String[] cities = citiesByState.get(randomState);
        return new Faker().options().option(cities);
    }


}

