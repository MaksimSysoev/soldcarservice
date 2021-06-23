package ru.sysoevm.carservise.rule.statechange;

import ru.sysoevm.carservise.utils.DateUtils;

import java.util.Calendar;

public class Service1Rule extends StateChangeRule {
    public Service1Rule() {
        super(DateUtils.generate(Calendar.MONTH, -12), DateUtils.generate(Calendar.DAY_OF_YEAR, -10), CarServiceStateEnum.SERVICE1);
    }
}
