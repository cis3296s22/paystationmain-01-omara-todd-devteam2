package edu.temple.cis.paystation;

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
public class RateStrategyGamma implements RateStrategy{
    @Override
    public void calculateTime(int amount){
    }
}
