package edu.depaul.ewright6.entities;

public class Reservation {
    private Integer id;
    private Integer customerId;
    private Integer flightId;
    private Object otherStuff;

    public Reservation() {

    }

    public Reservation(Integer id, Integer customerId, Integer flightId, Object otherStuff) {
        this.id = id;
        this.customerId = customerId;
        this.flightId = flightId;
        this.otherStuff = otherStuff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Object getOtherStuff() {
        return otherStuff;
    }

    public void setOtherStuff(Object otherStuff) {
        this.otherStuff = otherStuff;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", flightId=" + flightId +
                ", otherStuff=" + otherStuff +
                '}';
    }
}
