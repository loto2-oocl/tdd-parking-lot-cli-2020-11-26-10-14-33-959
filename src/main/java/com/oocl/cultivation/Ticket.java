package com.oocl.cultivation;

public class Ticket {
    private boolean used;

    public Ticket() {
        this.used = false;
    }

    public void setUsed() {
        this.used = true;
    }

    public boolean isUsed() {
        return used;
    }
}
