package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.ParkingTicket;

public class ExitSuccessModel {
    public ParkingTicket getTicket() {
        return ticket;
    }

    private ParkingTicket ticket;

    public ExitSuccessModel(ParkingTicket ticket) {
        this.ticket = ticket;
    }
}
