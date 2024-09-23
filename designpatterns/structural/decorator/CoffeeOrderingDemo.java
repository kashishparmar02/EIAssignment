package com.designpatterns.structural.decorator;

import com.designpatterns.util.LoggerUtil;

interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
        if (coffee.getDescription().contains("Milk")) {
            throw new IllegalArgumentException("Coffee already contains milk.");
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
        if (coffee.getDescription().contains("Sugar")) {
            throw new IllegalArgumentException("Coffee already contains sugar.");
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
}

public class CoffeeOrderingDemo {
    public static void main(String[] args) {
        try {
            Coffee simpleCoffee = new SimpleCoffee();
            LoggerUtil.info("Cost: $" + simpleCoffee.getCost() + ", Description: " + simpleCoffee.getDescription());

            Coffee milkCoffee = new MilkDecorator(simpleCoffee);
            LoggerUtil.info("Cost: $" + milkCoffee.getCost() + ", Description: " + milkCoffee.getDescription());

            Coffee sweetMilkCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
            LoggerUtil.info("Cost: $" + sweetMilkCoffee.getCost() + ", Description: " + sweetMilkCoffee.getDescription());
        } catch (IllegalArgumentException e) {
            LoggerUtil.error("Error: " + e.getMessage());
        }
    }
}
