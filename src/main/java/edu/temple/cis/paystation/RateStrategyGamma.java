package edu.temple.cis.paystation;
import java.time.DayOfWeek;
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
        Random rn = new Random();
        int dayOfTheWeekNumber = rn.nextInt(8);                 //just using random rn to determine if the day is a weekday or not
//        String DayOfTheWeek;
//        DayOfWeek dayOfWeek = DayOfWeek.of(dayOfTheWeekNumber);
//        if (dayOfTheWeekNumber < 5) {
//            DayOfTheWeek = "WEEKDAY";
//        } else {
//            DayOfTheWeek = "WEEKEND";
//        }
        if (dayOfTheWeekNumber < 6) {                   //if day of the week is monday - friday i.e weekday
            return rsB.calculateTime(amount);
        } else {                                        //if day of the week is saturday or sunday i.e weekday
            return rsA.calculateTime(amount);
        }

    }
}
