package edu.temple.cis.paystation;

public class Main {

    public static void main(String[] args) throws IllegalCoinException {
        PayStationImpl ps = new PayStationImpl();
        ps.startUI();
    }
}
