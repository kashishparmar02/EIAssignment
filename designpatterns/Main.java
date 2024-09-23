package com.designpatterns;

import com.designpatterns.behavioral.observer.StockMarketDemo;
import com.designpatterns.behavioral.strategy.PaymentDemo;
import com.designpatterns.creational.factory.VehicleManufacturingDemo;
import com.designpatterns.creational.builder.ComputerConfigurationDemo;
import com.designpatterns.structural.adapter.PaymentSystemIntegrationDemo;
import com.designpatterns.structural.decorator.CoffeeOrderingDemo;
import com.designpatterns.util.LoggerUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = getUserChoice(scanner);

            try {
                switch (choice) {
                    case 1:
                        LoggerUtil.info("Observer Pattern Demo:");
                        StockMarketDemo.main(args);
                        break;
                    case 2:
                        LoggerUtil.info("Strategy Pattern Demo:");
                        PaymentDemo.main(args);
                        break;
                    case 3:
                        LoggerUtil.info("Factory Pattern Demo:");
                        VehicleManufacturingDemo.main(args);
                        break;
                    case 4:
                        LoggerUtil.info("Builder Pattern Demo:");
                        ComputerConfigurationDemo.main(args);
                        break;
                    case 5:
                        LoggerUtil.info("Adapter Pattern Demo:");
                        PaymentSystemIntegrationDemo.main(args);
                        break;
                    case 6:
                        LoggerUtil.info("Decorator Pattern Demo:");
                        CoffeeOrderingDemo.main(args);
                        break;
                    case 0:
                        LoggerUtil.info("Exiting the program. Goodbye!");
                        break;
                    default:
                        LoggerUtil.warning("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                LoggerUtil.error("An unexpected error occurred: " + e.getMessage());
            }

            if (choice != 0) {
                LoggerUtil.info("Press Enter to continue...");
                scanner.nextLine();  // Consume the newline
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        LoggerUtil.info("\n=== Design Patterns Demo ===");
        LoggerUtil.info("1. Observer Pattern (Stock Market)");
        LoggerUtil.info("2. Strategy Pattern (Payment Processing)");
        LoggerUtil.info("3. Factory Pattern (Vehicle Manufacturing)");
        LoggerUtil.info("4. Builder Pattern (Computer Configuration)");
        LoggerUtil.info("5. Adapter Pattern (Legacy Payment System)");
        LoggerUtil.info("6. Decorator Pattern (Coffee Ordering)");
        LoggerUtil.info("0. Exit");
        LoggerUtil.info("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                LoggerUtil.warning("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
}
