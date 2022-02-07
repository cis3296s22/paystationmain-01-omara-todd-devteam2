package edu.temple.cis.paystation;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

/**
 * Implementation of RateStrategy for Gamma town.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Gamma town will calculate time purchased based on an alternating Strategy
 * 2) During weekdays time will be calculated using Beta Strategy
 * 3) During weekends time will be calculated using Alpha Strategy
 * <p>
 */
public class RateStrategyGamma implements RateStrategy {
    private final RateStrategy rsA = new RateStrategyAlpha();
    private final RateStrategy rsB = new RateStrategyBeta();
    @Override
    public double calculateTime(int amount) {
        //date = the systems current date, then dayOfTheWeek number is the systems current date turned into a number representing day of the week
        LocalDate date = LocalDate.now();
        int dayOfTheWeekNumber = getDayNumber(date);
        //if day of the week is monday - friday i.e weekday
        if (dayOfTheWeekNumber < 6) {
            return rsB.calculateTime(amount);
            //if day of the week is saturday or sunday i.e weekday
        } else {
            return rsA.calculateTime(amount);
        }

    }


    //function that when passed a specific date, returns the day of the week as a number
    //where monday = 1 and sunday = 7
    public static int getDayNumber(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

}
