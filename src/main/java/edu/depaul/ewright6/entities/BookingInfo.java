package edu.depaul.ewright6.entities;

import java.util.ArrayList;

public class BookingInfo {
    private Integer cutomerId;
    private ArrayList<Integer> flightIds;
    private String paymentInfo;

    public BookingInfo() {
    }

    public BookingInfo(Integer cutomerId, ArrayList<Integer> flightIds, String paymentInfo) {
        this.cutomerId = cutomerId;
        this.flightIds = flightIds;
        this.paymentInfo = paymentInfo;
    }

    public Integer getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(Integer cutomerId) {
        this.cutomerId = cutomerId;
    }

    public ArrayList<Integer> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(ArrayList<Integer> flightIds) {
        this.flightIds = flightIds;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @Override
    public String toString() {
        return "BookingInfo{" +
                "cutomerId=" + cutomerId +
                ", flightIds=" + flightIds +
                ", paymentInfo='" + paymentInfo + '\'' +
                '}';
    }
}
