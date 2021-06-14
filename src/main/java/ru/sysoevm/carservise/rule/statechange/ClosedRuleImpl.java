package ru.sysoevm.carservise.rule.statechange;

import java.sql.Date;
import java.util.Calendar;

public class ClosedRuleImpl extends StateChangeRule {

    public Date getFrom() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -37);
        Date from = new Date(today.getTimeInMillis());
        return from;
    }

    public Date getTo() {
        return null;
    }

    public CarServiceStateEnum getState() {
        return CarServiceStateEnum.CLOSED;
    }

}
