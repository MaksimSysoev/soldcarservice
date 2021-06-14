package ru.sysoevm.carservise.rule.supportnotify;

import ru.sysoevm.carservise.rule.statechange.CarServiceStateEnum;

import java.sql.Date;
import java.util.Calendar;

public class Service1SupportRule extends SupportNotifyRule {

    public Date getFrom() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -11);
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
        return CarServiceStateEnum.SERVICE2;
    }

}
