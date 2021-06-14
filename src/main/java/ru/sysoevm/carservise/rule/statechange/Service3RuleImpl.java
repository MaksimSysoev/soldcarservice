package ru.sysoevm.carservise.rule.statechange;

import java.util.Calendar;
import java.sql.Date;

public class Service3RuleImpl extends StateChangeRule {

    public Date getFrom() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -24);
        Date from = new Date(today.getTimeInMillis());;
        return from;
    }

    public Date getTo() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -37);
        Date to = new Date(today.getTimeInMillis());;
        return to;
    }

    public CarServiceStateEnum getState() {
        return CarServiceStateEnum.SERVICE3;
    }
}
