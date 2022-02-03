package edu.temple.cis.paystation;

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
        Random rn = new Random();
        int dayOfTheWeekNumber = rn.nextInt(8);                 //just using random rn to determine if the day is a weekday or not
        if (dayOfTheWeekNumber < 6) {                   //if day of the week is monday - friday i.e weekday
            return rsA.calculateTime(amount);
        } else {                                        //if day of the week is saturday or sunday i.e weekday
            return amount;
        }
    }
}

