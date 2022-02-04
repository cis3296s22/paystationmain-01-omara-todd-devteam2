package edu.temple.cis.paystation;

/**
 * Implementation of RateStrategy for Alpha town.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Alpha town will calculate time purchased based on a linear rate
 * 2) 5c buys two minutes
 * 3) @args the total amount of money deposited
 * 4) @return a double value of the total time purchased
 * <p>
 */
public class RateStrategyAlpha implements RateStrategy {
    @Override
    public double calculateTime(int amount) {
        return (amount * 2.0) / 5.0;
    }
}
