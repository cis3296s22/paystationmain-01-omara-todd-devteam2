package edu.temple.cis.paystation;

interface RateStrategy {

    /**
     * Calculate the time that will be purchased based on a specified rate strategy.
     *
     * @return
     */

    int calculateTime(int amount);

}
