package com.designpatterns.behavioral.observer;

import com.designpatterns.util.LoggerUtil;
import java.util.ArrayList;
import java.util.List;

interface StockObserver {
    void update(String stockSymbol, double price);
}

class StockMarket {
    private List<StockObserver> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void setStockInfo(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        notifyObservers();
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, price);
        }
    }
}

class StockTrader implements StockObserver {
    private String name;

    public StockTrader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double price) {
        LoggerUtil.log(name + " received update: " + stockSymbol + " is now $" + price);
    }
}

public class StockMarketDemo {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        StockTrader trader1 = new StockTrader("Trader 1");
        StockTrader trader2 = new StockTrader("Trader 2");

        stockMarket.addObserver(trader1);
        stockMarket.addObserver(trader2);

        stockMarket.setStockInfo("AAPL", 150.50);
        stockMarket.setStockInfo("GOOGL", 2750.75);
    }
}