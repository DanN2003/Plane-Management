# Plane-Management
Plane Management System ( Java )

Person.java: This class represents a person, encapsulating their personal details such as name, surname, and email address. It serves as a base class for storing information about passengers.

Ticket.java: This class represents a ticket, associating a passenger (Person) with a specific seat on a flight. It includes details such as the row number, seat number, and the price of the ticket.

PlaneManagement.java: This is the main class that drives the application. It manages a list of passengers and their corresponding tickets. The class includes functionalities for:

Adding new passengers.
Assigning tickets to passengers.
Retrieving and displaying passenger and ticket information.
Output Files: After the operations are performed, the system generates a text file (1A.txt) containing the ticket details, including the seat assignment and passenger information. This file serves as a record of the transaction.

How It Works
Adding a Passenger: You can add a new passenger by providing their details (name, surname, email). These details are stored in an instance of the Person class.

Issuing a Ticket: Once a passenger is added, you can issue a ticket by specifying the seat details (row and seat number) and the price. The ticket information, along with the passenger's details, is encapsulated in the Ticket class.

Saving and Retrieving Information: The system saves the ticket details to a text file named after the seat assignment (e.g., 1A.txt). This file contains the passenger’s name, seat number, and ticket price.

Usage: Run the PlaneManagement class to start the application. It will guide you through adding passengers and issuing tickets, and automatically save the information to the corresponding text file.

The content of a sample ticket file (1A.txt) might look like this:

Row: 1
Seat: A
Price: 200.0
Ticket holder information:
Name: a
Surname: a
Email: a
