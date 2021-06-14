package ru.sysoevm.carservise.rule.statechange;

import java.sql.Date;
import java.util.Calendar;

public class Service1RuleImpl extends StateChangeRule {

    public Date getFrom() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, -10);
        Date tenDaysBefore = new Date(today.getTimeInMillis());
        return tenDaysBefore;
    }

    public Date getTo() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -12);
        Date oneYearBefore = new Date(today.getTimeInMillis());;
        return oneYearBefore;
    }

    public CarServiceStateEnum getState() {
        return CarServiceStateEnum.SERVICE1;
    }
}
