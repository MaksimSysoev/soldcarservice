package ru.sysoevm.carservise.rule.statechange;

import ru.sysoevm.carservise.utils.DateUtils;

import java.util.Calendar;

public class Service2Rule extends StateChangeRule {
    public Service2Rule() {
        super(DateUtils.generate(Calendar.MONTH, -24), DateUtils.generate(Calendar.MONTH, -12), CarServiceStateEnum.SERVICE2);
    }
}
