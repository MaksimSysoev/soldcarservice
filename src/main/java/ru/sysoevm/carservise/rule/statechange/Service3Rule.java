package ru.sysoevm.carservise.rule.statechange;

import ru.sysoevm.carservise.utils.DateUtils;

import java.util.Calendar;

public class Service3Rule extends StateChangeRule  {

    public Service3Rule() {
        super(DateUtils.generate(Calendar.MONTH, -37), DateUtils.generate(Calendar.MONTH, -24), CarServiceStateEnum.SERVICE3);
    }
}
