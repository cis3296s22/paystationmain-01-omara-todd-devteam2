package edu.temple.cis.paystation;

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
public class RateStrategyOmega implements RateStrategy{
    @Override
    public double calculateTime(int amount){
        return 0;
    }
}

