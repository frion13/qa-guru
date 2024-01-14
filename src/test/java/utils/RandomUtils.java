package utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RandomUtils {
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


    public static String getRandomPhone() {
        return String.format("+%s (%s) %s - %s - %s", getRandomInt(1, 9), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        int index = getRandomInt(0, genders.length - 1);
        return genders[index];
    }


    public static String getRandomMonth() {
        String[] monthNames = {"January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};

        int randomIndex = getRandomInt(0, monthNames.length - 1);
        return monthNames[randomIndex];

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

        int index = getRandomInt(0, subjects.length - 1);
        return subjects[index];

    }

    public static String getRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        int index = getRandomInt(0, hobbies.length - 1);
        return hobbies[index];
    }

}

