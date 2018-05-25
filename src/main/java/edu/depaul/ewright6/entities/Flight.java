package edu.depaul.ewright6.entities;

public class Flight {
    private Integer id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String dateTime;
    private Object otherStuff;

    public Flight() {
    }

    public Flight(Integer id, String flightNumber, String origin, String destination, String dateTime, Object otherStuff) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.otherStuff = otherStuff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Object getOtherStuff() {
        return otherStuff;
    }

    public void setOtherStuff(Object otherStuff) {
        this.otherStuff = otherStuff;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", otherStuff=" + otherStuff +
                '}';
    }
}
