import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private int row;
    private char seat;
    private double price;
    private Person person;

    public Ticket(int row, char seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getSeat() {
        return seat;
    }

    public void setSeat(char seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void printTicketInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("Ticket holder information:");
        person.printPersonInfo();
    }

    public void save() {
        String fileName = row + String.valueOf(seat) + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: " + price + "\n");
            writer.write("Ticket holder information:\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            System.out.println("Ticket information saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving ticket information to file: " + e.getMessage());
        }
    }
}
