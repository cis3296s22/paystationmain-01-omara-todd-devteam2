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

    // Constructor initializes instance variables
    public PayStationImpl() {
        insertedSoFar = timeBought = totalMoney = 0;
        coinMap = new HashMap<>();
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

        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
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

    public void startInterface() throws IllegalCoinException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pay Station Options menu:");
        System.out.println(" 1) Deposit coin\n 2) Display\n 3) Buy Ticket " +
                "\n 4) Cancel \n 5) Empty (Admin)\n 6) Change Rate Strategy (Admin)\n");

        // loop {
        System.out.print("Option select >> ");
        int input = scanner.nextInt();
        switch (input) {
            case 1:     //Customer deposits a coin , maybe should be a loop since costumer can add many coins ?
                this.coinSelectInterface();
                break;
            case 2:     //displays time bought
                System.out.printf("Time Bought: %d\n", this.readDisplay());
                break;
            case 3:
                Receipt receipt = this.buy();
                System.out.println("Thank you for your purchase\nPlease take your receipt " + receipt.value() + "\nParking Time: " + this.readDisplay());
                break;
            case 4:
                this.cancel();
                break;
            case 5:
                this.empty();
                break;
            case 6:
                //TODO: implement strategy interface
            default:
                System.out.println("Please make a valid selection");
        }
        // }loop
        scanner.close();
    }

    public void rateStrategiesInterface() throws IllegalCoinException {
        //this will be the interface that lets the user choose which town/rate strategy they would like to use
        Scanner sc = new Scanner(System.in);
        
    }

    public void coinSelectInterface() throws IllegalCoinException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Coin to deposit:");
        System.out.println(" 1) 5\u00A2\n 2) 10\u00A2\n 3) 25\u00A2\n " +
                "0) Return to \"Pay Station options menu\"");

        // loop() {
        System.out.print("Select Coin >> ");
        int input = scanner.nextInt();
        switch (input) {
            case 0:
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
                System.out.println("Please make a valid selection");
        }
        // } loop
        scanner.close();
    }


}
