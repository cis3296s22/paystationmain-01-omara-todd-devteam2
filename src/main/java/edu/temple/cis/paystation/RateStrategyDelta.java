package edu.temple.cis.paystation;

/**
 * Implementation of RateStrategy for Delta town.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Delta town will calculate time purchased based on a linear rate
 * 2) 5c buys one minute of time
 * 3) @args the total amount of money
 * 4) @return an integer total amount of time
 * <p>
 */
public class RateStrategyDelta implements RateStrategy{
    @Override
    public int calculateTime(int amount){
        return amount / 5;
    }
}
