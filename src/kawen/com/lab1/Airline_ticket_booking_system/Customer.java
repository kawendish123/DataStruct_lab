package kawen.com.lab1.Airline_ticket_booking_system;

public class Customer {
    String name;
    int amount;
    char rank;
    int idInfo;
    Customer next;

    public Customer(String name, int amount, char rank, int idInfo) {
        this.name = name;
        this.amount = amount;
        this.rank = rank;
        this.idInfo = idInfo;
        this.next = null;
    }
}