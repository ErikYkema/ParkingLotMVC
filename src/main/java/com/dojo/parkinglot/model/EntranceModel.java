package com.dojo.parkinglot.model;

import com.dojo.parkinglot.domain.FreeSpaceCounter;

public class EntranceModel {
    private FreeSpaceCounter freeSpaceCounter;

    public FreeSpaceCounter getFreeSpaceCounter() {
        return freeSpaceCounter;
    }

    public EntranceModel(FreeSpaceCounter freeSpaceCounter) {
        this.freeSpaceCounter = freeSpaceCounter;
    }
}
