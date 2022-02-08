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
 * 5) @args the total amount of money deposited
 * 6) @return a double value of the total time purchased
 * <p>
 */
public class RateStrategyBeta implements RateStrategy {
    private final RateStrategy rs = new RateStrategyAlpha();

    @Override
    public int calculateTime(int amount) {
        if (amount < 150) {
            return rs.calculateTime(amount);
        } else if (amount < 350) {
            return Math.round((amount - 150) * ((float) 3 / 10) + 60);
        }
        return Math.round(((amount - 350) / (float)5) + 120);
    }
}
