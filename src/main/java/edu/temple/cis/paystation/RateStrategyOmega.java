package edu.temple.cis.paystation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

/**
 * Implementation of RateStrategy for Omega town.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Omega town will calculate time purchased based on an Alternating Strategy
 * 2) During weekdays time will be calculated using Alpha Strategy
 * 3) During weekends parking will be free
 * <p>
 */
public class RateStrategyOmega implements RateStrategy {

    private final RateStrategy rsA = new RateStrategyAlpha();

    @Override
    public double calculateTime(int amount) {
        //date = the systems current date, then dayOfTheWeek number is the systems current date turned into a number representing day of the week
        LocalDate date = LocalDate.now();
        int dayOfTheWeekNumber = getDayNumber(date);

        if (dayOfTheWeekNumber < 6) {                   //if day of the week is monday - friday i.e weekday
            return rsA.calculateTime(amount);
        } else {                                        //if day of the week is saturday or sunday i.e weekday
            return amount;
        }
    }

    //function that when passed a specific date, returns the day of the week as a number
    //where monday = 1 and sunday = 7
    public static int getDayNumber(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }
}

