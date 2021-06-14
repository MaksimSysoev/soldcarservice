package ru.sysoevm.carservise.data;

import java.sql.Date;

public class SoldCarServiceDTO {

    private int id;

    private Date purchase_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPurchase_datetime() {
        return purchase_datetime;
    }

    public void setPurchase_datetime(Date purchase_datetime) {
        this.purchase_datetime = purchase_datetime;
    }
}
