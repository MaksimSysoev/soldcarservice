package ru.sysoevm.carservise.rule.statechange;

import java.sql.Date;
import java.util.Calendar;

public class Service2RuleImpl extends StateChangeRule {

    public Date getFrom() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -12);
        Date from = new Date(today.getTimeInMillis());;
        return from;
    }

    public Date getTo() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -24);
        Date to = new Date(today.getTimeInMillis());;
        return to;
    }

    public CarServiceStateEnum getState() {
        return CarServiceStateEnum.SERVICE2;
    }
}
