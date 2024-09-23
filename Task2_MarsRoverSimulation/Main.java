import commands.*;
import grid.*;
import rover.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        configureLogger();

        Scanner scanner = new Scanner(System.in);

        try {
            logger.info("Starting the Mars Rover Program");

            // Initialize the grid with proper validation
            System.out.print("Enter grid width: ");
            int width = readValidInt(scanner);
            System.out.print("Enter grid height: ");
            int height = readValidInt(scanner);

            Grid grid = new Grid(width, height);
            logger.info("Grid initialized with width: " + width + " and height: " + height);

            // Add obstacles
            System.out.print("Enter number of obstacles: ");
            int obstacleCount = readValidInt(scanner);

            for (int i = 0; i < obstacleCount; i++) {
                try {
                    System.out.print("Enter obstacle " + (i + 1) + " x coordinate: ");
                    int x = readValidInt(scanner);
                    System.out.print("Enter obstacle " + (i + 1) + " y coordinate: ");
                    int y = readValidInt(scanner);
                    
                    if (x < 0 || x >= width || y < 0 || y >= height) {
                        logger.warning("Invalid obstacle coordinates. They must be within grid bounds.");
                        System.out.println("Invalid obstacle coordinates. Please enter values within grid bounds.");
                        i--; // Retake this obstacle input
                        continue;
                    }
                    grid.addObstacle(new Obstacle(x, y));
                    logger.info("Added obstacle at (" + x + ", " + y + ")");
                } catch (InputMismatchException e) {
                    logger.log(Level.WARNING, "Invalid input for obstacle coordinates.", e);
                    scanner.nextLine(); // Clear the invalid input
                    i--; // Retake this obstacle input
                }
            }

            // Initialize the rover with validation
            System.out.print("Enter rover starting x coordinate: ");
            int roverX = readValidInt(scanner);
            System.out.print("Enter rover starting y coordinate: ");
            int roverY = readValidInt(scanner);

            if (roverX < 0 || roverX >= width || roverY < 0 || roverY >= height) {
                throw new IllegalArgumentException("Rover starting position must be within grid bounds.");
            }

            System.out.print("Enter rover starting direction (N, E, S, W): ");
            Direction direction = getDirectionFromInput(scanner.next().toUpperCase());

            Rover rover = new Rover(roverX, roverY, direction, grid);
            logger.info("Rover initialized at (" + roverX + ", " + roverY + ") facing " + direction);

            // Execute commands until 'Q' is entered
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.print("Enter command (M, L, R) or Q to quit: ");
                String input = scanner.next().toUpperCase();

                Command command = null;
                switch (input) {
                    case "M":
                        command = new MoveCommand();
                        break;
                    case "L":
                        command = new TurnLeftCommand();
                        break;
                    case "R":
                        command = new TurnRightCommand();
                        break;
                    case "Q":
                        continueRunning = false;
                        logger.info("Exiting program as 'Q' command received");
                        continue;
                    default:
                        logger.warning("Invalid command input received: " + input);
                        System.out.println("Invalid command. Please enter M, L, R, or Q.");
                        continue;
                }

                try {
                    if (command != null) {
                        command.execute(rover);
                        logger.info("Executed command: " + input);
                        // Print current position and status after each command
                        System.out.println("Current Position: " + rover.getPosition());
                        System.out.println(rover.getStatusReport());
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error executing command: " + input, e);
                }
            }

            // Print final position and status
            System.out.println("Final Position: " + rover.getPosition());
            System.out.println(rover.getStatusReport());
            logger.info("Program finished. Final position: " + rover.getPosition());

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error occurred.", e);
        } finally {
            scanner.close();
            logger.info("Scanner closed. Program terminated.");
        }
    }

  
    private static void configureLogger() {
        try {
            // Disable default console handler
            LogManager.getLogManager().reset();

            // Create a console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);

            // Create a file handler
            FileHandler fileHandler = new FileHandler("rover.log", true); // true for appending to the log file
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);
            logger.info("Logging configuration set up successfully.");
        } catch (IOException e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }

    private static int readValidInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.warning("Invalid integer input detected. Please enter a valid integer.");
                System.out.print("Invalid input. Please enter a valid integer: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static Direction getDirectionFromInput(String input) {
        switch (input) {
            case "N":
                return Direction.NORTH;
            case "E":
                return Direction.EAST;
            case "S":
                return Direction.SOUTH;
            case "W":
                return Direction.WEST;
            default:
                throw new IllegalArgumentException("Invalid direction. Please enter N, E, S, or W.");
        }
    }
}
