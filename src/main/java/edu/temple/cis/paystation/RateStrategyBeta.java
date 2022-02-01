package edu.temple.cis.paystation;

/**
 * Implementation of RateStrategy for Beta town.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Beta town will calculate time purchased based on a progressive rate
 * 2) 5c buys two minutes for the first hour
 * 3) 5c buys one and a half minutes after the first hour and before the second hour
 * 4) 5c buys one minute after the second hour
 * <p>
 */
public class RateStrategyBeta implements RateStrategy{
    @Override
    public int calculateTime(int amount){
        return 0;
    }
}
