# Hotel-Reservation-Application
***Project from the Udacity course: [JAVA Programming Course](https://www.udacity.com/course/java-programming-nanodegree--nd079).***

This is a JAVA application that allows users to reserve a hotel room based on the desired check-in and check-out date and enables the admin to manage the customers and rooms.


## User Scenarios
The application provides four user scenarios:
- Creating a customer account. The user needs to first create a customer account before they can create a reservation.
- Searching for rooms. The app should allow the user to search for available rooms based on provided checkin and checkout dates. If the application has available rooms for the specified date range, a list of the corresponding rooms will be displayed to the user for choosing.
- Booking a room. Once the user has chosen a room, the app will allow them to book the room and create a reservation.
- Viewing reservations. After booking a room, the app allows customers to view a list of all their reservations.
## Admin Scenarios
The application provides four administrative scenarios:
- Displaying all customers accounts.
- Viewing all of the rooms in the hotel.
- Viewing all of the hotel reservations.
- Adding a room to the hotel application.
## Reserving a Room
The application allows customers to reserve a room. Here are the specifics:
- **Avoid conflicting reservations.** A single room may only be reserved by a single customer per check-in and check-out date range.
- **Search for recommended rooms.** If there are no available rooms for the customer's date range, a search will be performed that displays recommended rooms on alternative dates. The recommended room search will add seven days to the original check-in and check-out dates to see if the hotel has any availabilities and then display the recommended rooms/dates to the customer.
## Room Requirements
- **Room cost.** Rooms will contain a price per night. When displaying rooms, paid rooms will display the price per night and free rooms will display "Free".
- **Unique room numbers.** Each room will have a unique room number, meaning that no two rooms can have the same room number.
- **Room type.** Rooms can be either single occupant or double occupant.
## Customer Requirements
The application will have customer accounts. Each account has:
- **A unique email for the customer.** RegEx is used to check that the email is in the correct format (i.e., name@domain.com).
- **A first name and last name.**
