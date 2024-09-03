import java.util.Scanner;

public class PlaneManagement {
    private static final int TOTAL_ROWS = 4; // Total number of rows on the plane
    private static final int[] SEATS_PER_ROW = {14, 12, 12, 14}; // Number of seats in each row
    private static final char[] ROW_LETTERS = {'A', 'B', 'C', 'D'}; // Row letters
    private static final int MAX_SEATS = 14; // Maximum number of seats per row
    private static int[][] seats = new int[TOTAL_ROWS][]; // 2D array to represent seat status (0 - available, 1 - sold)
    private static Ticket[] ticketList = new Ticket[MAX_SEATS * TOTAL_ROWS]; // Array to store all tickets sold
    private static Scanner scanner = new Scanner(System.in); // Scanner object for user input
    private static int ticketIndex = 0; // Index to keep track of tickets in the ticketList array

    public static void main(String[] args) {
        initializeSeats(); // Initialize all seats as available (0)
        System.out.println("Welcome to the Plane Management application");

        // Display user menu and handle user input
        int option;
        do {
            displayMenu();
            option = getUserOption();
            switch (option) {
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketsInfo();
                    break;
                case 6:
                    searchTicket();
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 6.");
            }
        } while (option != 0);
    }

    // Method to display the user menu
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Buy a seat");
        System.out.println("2. Cancel a seat");
        System.out.println("3. Find first available seat");
        System.out.println("4. Show seating plan");
        System.out.println("5. Print tickets information and total sales");
        System.out.println("6. Search ticket");
        System.out.println("0. Quit");
        System.out.print("Enter your choice: ");
    }

    // Method to get user option from the menu
    private static int getUserOption() {
        return scanner.nextInt();
    }

    // Method to initialize all seats as available (0)
    private static void initializeSeats() {
        for (int i = 0; i < TOTAL_ROWS; i++) {
            seats[i] = new int[SEATS_PER_ROW[i]];
            for (int j = 0; j < SEATS_PER_ROW[i]; j++) {
                seats[i][j] = 0;
            }
        }
    }

    // Method to buy a seat
private static void buySeat() {
    System.out.print("Enter the row letter (A-D): ");
    char rowLetter = scanner.next().toUpperCase().charAt(0);

    // Validate row letter
    int rowIndex = rowLetter - 'A';
    if (rowIndex < 0 || rowIndex >= TOTAL_ROWS) {
        System.out.println("Invalid row letter. Please enter a letter between A and D.");
        return;
    }

    System.out.print("Enter the seat number (1-" + SEATS_PER_ROW[rowIndex] + "): ");
    int seatNumber = scanner.nextInt();
    // Validate seat number
    if (seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowIndex]) {
        System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW[rowIndex] + ".");
        return;
    }

    // Convert seat number to zero-based index
    int seatIndex = seatNumber - 1;

    if (seats[rowIndex][seatIndex] == 0) {
        // Ask for person's information
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();

        // Create a new Person object
        Person person = new Person(name, surname, email);

        // Calculate price based on seat location
        double price;
        if (rowIndex < 5) {
            price = 200.0; // Seats from 1A to 5D
        } else if (rowIndex < 9) {
            price = 150.0; // Seats from 6A to 9D
        } else {
            price = 180.0; // Seats from 10A to 14D
        }

        // Create a new Ticket object
        Ticket ticket = new Ticket(rowIndex + 1, rowLetter, price, person);

        // Mark seat as sold
        seats[rowIndex][seatIndex] = 1;

        // Add the ticket to the ticket list
        ticketList[ticketIndex++] = ticket;

        // Save ticket information to file
        ticket.save();

        System.out.println("Seat " + seatNumber + rowLetter + " purchased successfully.");
    } else {
        System.out.println("Seat " + seatNumber + rowLetter + " is already sold or invalid.");
    }
}

    // Method to cancel a seat
    private static void cancelSeat() {
        System.out.print("Enter the row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);

        // Validate row letter
        int rowIndex = rowLetter - 'A';
        if (rowIndex < 0 || rowIndex >= TOTAL_ROWS) {
            System.out.println("Invalid row letter. Please enter a letter between A and D.");
            return;
        }

        System.out.print("Enter the seat number (1-" + SEATS_PER_ROW[rowIndex] + "): ");
        int seatNumber = scanner.nextInt();
        // Validate seat number
        if (seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowIndex]) {
            System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW[rowIndex] + ".");
            return;
        }

        // Convert seat number to zero-based index
        int seatIndex = seatNumber - 1;

        if (seats[rowIndex][seatIndex] == 1) {
            for (int i = 0; i < ticketIndex; i++) {
                Ticket ticket = ticketList[i];
                if (ticket.getRow() == rowIndex + 1 && ticket.getSeat() == rowLetter) {
                    // Remove the ticket from the ticket list
                    ticketList[i] = null;
                    // Mark seat as available again
                    seats[rowIndex][seatIndex] = 0;
                    System.out.println("Seat " + seatNumber + rowLetter + " cancelled successfully.");
                    return;
                }
            }
        } else {
            System.out.println("No ticket found for this seat.");
        }
    }

    // Method to find the first available seat
    private static void findFirstAvailable() {
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW[i]; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + ROW_LETTERS[i] + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats found.");
    }

    // Method to show seating plan
    private static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int i = 0; i < TOTAL_ROWS; i++) {
            System.out.print(ROW_LETTERS[i] + " ");
            for (int j = 0; j < SEATS_PER_ROW[i]; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    // Method to print tickets information and total sales
    private static void printTicketsInfo() {
        double totalSales = 0.0;
        System.out.println("Tickets Information:");
        for (Ticket ticket : ticketList) {
            if (ticket != null) {
                ticket.printTicketInfo();
                totalSales += ticket.getPrice();
            }
        }
        System.out.println("Total Sales: £" + totalSales);
    }

    // Method to search for a ticket
    private static void searchTicket() {
        System.out.print("Enter the row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);

        // Validate row letter
        int rowIndex = rowLetter - 'A';
        if (rowIndex < 0 || rowIndex >= TOTAL_ROWS) {
            System.out.println("Invalid row letter. Please enter a letter between A and D.");
            return;
        }

        System.out.print("Enter the seat number (1-" + SEATS_PER_ROW[rowIndex] + "): ");
        int seatNumber = scanner.nextInt();
        // Validate seat number
        if (seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowIndex]) {
            System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW[rowIndex] + ".");
            return;
        }

        // Convert seat number to zero-based index
        int seatIndex = seatNumber - 1;

        if (seats[rowIndex][seatIndex] == 1) {
            for (Ticket ticket : ticketList) {
                if (ticket != null && ticket.getRow() == rowIndex + 1 && ticket.getSeat() == rowLetter) {
                    System.out.println("Ticket found:");
                    ticket.printTicketInfo();
                    return;
                }
            }
        } else {
            System.out.println("This seat is available.");
        }
    }
}
