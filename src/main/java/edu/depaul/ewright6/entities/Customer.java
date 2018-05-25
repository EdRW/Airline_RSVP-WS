package edu.depaul.ewright6.entities;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String otherStuff;

    public Customer() {
    }


    public Customer(Integer id, String firstName, String lastName, String address, String otherStuff) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.otherStuff = otherStuff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOtherStuff() {
        return otherStuff;
    }

    public void setOtherStuff(String otherStuff) {
        this.otherStuff = otherStuff;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", otherStuff=" + otherStuff +
                '}';
    }
}
