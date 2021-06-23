package ru.sysoevm.carservise.rule.statechange;

import ru.sysoevm.carservise.utils.DateUtils;

import java.util.Calendar;

public class ClosedRule extends StateChangeRule {
    public ClosedRule() {
        super(null, DateUtils.generate(Calendar.MONTH, -37), CarServiceStateEnum.CLOSED);
    }
}
