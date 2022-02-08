package edu.temple.cis.paystation;

import java.util.*;

/**
 * Implementation of the pay station.
 * <p>
 * Responsibilities:
 * <p>
 * 1) Accept payment;
 * 2) Calculate parking time based on payment;
 * 3) Know earning, parking time bought;
 * 4) Issue receipts;
 * 5) Handle buy and cancel events.
 * <p>
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * <p>
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {

    private int insertedSoFar, timeBought, totalMoney;
    private Map<Integer, Integer> coinMap;
    private RateStrategy rateStrategy;
    private final Scanner scanner;
    private int input;

    // Constructor initializes instance variables
    public PayStationImpl() {
        insertedSoFar = timeBought = totalMoney = input = 0;
        coinMap = new HashMap<>();
        rateStrategy = new RateStrategyBeta();
        scanner = new Scanner(System.in);
    }

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {

        switch (coinValue) {
            case 5:
            case 10:
            case 25:
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }

        /*
         * getOrDefault checks if a given key is present in a map
         * @returns the value if it exists, or the 'defaultValue' if it does not
         * add 1 to whatever the result of getOrDefault is and place that value in the map
         */
        coinMap.put(coinValue, coinMap.getOrDefault(coinValue, 0) + 1);

//        insertedSoFar += coinValue;
//        timeBought = insertedSoFar / 5 * 2;

        timeBought = rateStrategy.calculateTime(insertedSoFar);
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        totalMoney += insertedSoFar;
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        Map<Integer, Integer> tempMap = coinMap;
        coinMap = new HashMap<>();
        reset();
        return tempMap;
    }

    private void reset() {
        timeBought = insertedSoFar = 0;
        coinMap.clear();
    }

    @Override
    public int empty() {
        int temp = totalMoney;
        totalMoney = 0;
        return temp;
    }

    /**
     * Start the main UI for the PayStation via CLI.
     * <p>
     * Responsibilities:
     * <p>
     * 1) Display a prompt to the user via CLI
     * 2) Take Integer input from the user
     * 3) Run the specified PayStation method
     */
    public void startOptionsUI() throws IllegalCoinException {
        boolean flag = false;
        while (!flag) {

            System.out.print("\nPay Station Options menu:\n" +
                    " 1) Deposit coin\n 2) Display\n 3) Buy Ticket\n" +
                    " 4) Cancel \n 5) Empty (Admin)\n 6) Change Rate Strategy (Admin)\n" +
                    " 9) Exit program\n" +
                    "   Option select >> ");

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Input must be integer!");
                scanner.next();
            }

            switch (input) {
                case 1:     // Deposit coin(s)
                    this.coinSelectUI();
                    break;
                case 2:     // Print time display to the cli
                    System.out.printf("Time Bought: %d\n", this.readDisplay());
                    break;
                case 3:     // Print receipt value and time bought to cli
                    System.out.printf("Thank you for your purchase, Please take your receipt: " +
                            "%d\nParking Time: %d", this.buy().value(), this.readDisplay());
                    break;
                case 4:     // Cancel current Transaction
                    this.cancel();
                    break;
                case 5:     // Empty the PayStation
                    this.empty();
                    break;
                case 6:     // Change Rate Strategy
                    this.rateStrategySelectUI();
                    break;
                case 9:
                    flag = true;
                    break;
                default:
                    System.err.println("\n*Enter an integer 1 - 6*\n");
            }
        }
        scanner.close();
    }

    /**
     * A Cli interface menu to select which coin to deposit into the PayStation.
     * <p>
     * Responsibilities:
     * <p>
     * 1) Display a prompt to the user via CLI
     * 2) Take integer input from the user
     * 3) Call addPayment with pre-defined value 5c, 10c, 25c
     * 4) Return to main PayStation UI when User is done selecting coins.
     */
    public void coinSelectUI() throws IllegalCoinException {
        boolean flag = false;
        while (!flag) {
            System.out.print("\n\nSelect Coin to deposit:\n" +
                    " 1) 5\u00A2\n 2) 10\u00A2\n 3) 25\u00A2\n" +
                    " 0) Return to \"Pay Station options menu\"\n" +
                    " Select Coin >> ");

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Input must be integer!");
                scanner.next();
            }

            switch (input) {
                case 0:
                    flag = true;
                    break;
                case 1:
                    this.addPayment(5);
                    break;
                case 2:
                    this.addPayment(10);
                    break;
                case 3:
                    this.addPayment(25);
                    break;
                default:
                    System.err.println("\n*Enter an integer 1 - 3*\n");
            }
        }
    }

    /**
     * A Cli interface menu to select which rateStrategy the PayStation will use.
     * <p>
     * Responsibilities:
     * <p>
     * 1) Display a prompt to the user via CLI
     * 2) Take integer input from the user
     * 3) User selection will determine RateStrategy used by the PayStation
     * 4) Return to main PayStation UI when User is done selecting coins.
     */
    public void rateStrategySelectUI() {
        boolean flag = false;
        while (!flag) {
            System.out.print("\nSelect a Rate Strategy:\n" +
                    " 1) Linear Alpha\n 2) Linear Beta\n 3) Alternating Alpha\n" +
                    " 4) Alternating Beta\n 5) Progressive\n" +
                    " 0) Return to \"Pay Station options menu\"\n" +
                    " Select Strategy >> ");

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Input must be integer!");
                scanner.next();
            }

            switch (input) {
                case 0:
                    flag = true;
                    break;
                case 1:
                    rateStrategy = new RateStrategyBeta();
                    break;
                case 2:
                    rateStrategy = new RateStrategyGamma();
                    break;
                case 3:
                    rateStrategy = new RateStrategyAlpha();
                    break;
                case 4:
                    rateStrategy = new RateStrategyDelta();
                    break;
                case 5:
                    rateStrategy = new RateStrategyOmega();
                    break;
                default:
                    System.err.println("\n*Enter an integer 1 - 5*\n");
            }
        }
    }

}
