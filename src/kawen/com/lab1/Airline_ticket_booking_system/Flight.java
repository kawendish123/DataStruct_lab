package kawen.com.lab1.Airline_ticket_booking_system;

public class Flight {
        String destination;
        String flightNo;
        String planeNo;
        int weekDay;
        int customerAmount;
        int freeAmount;
        int[] price;
        Customer custName;
        Customer replName;
        Flight next;

public Flight(String destination, String flightNo, String planeNo, int weekDay,
        int customerAmount, int freeAmount, int[] price) {
        this.destination = destination;
        this.flightNo = flightNo;
        this.planeNo = planeNo;
        this.weekDay = weekDay;
        this.customerAmount = customerAmount;
        this.freeAmount = freeAmount;
        this.price = price;
        this.custName = null;
        this.replName = null;
        this.next = null;
        }
        }