package ru.sysoevm.carservise.process;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sysoevm.carservise.rule.*;
import ru.sysoevm.carservise.rule.statechange.ClosedRuleImpl;
import ru.sysoevm.carservise.rule.statechange.Service1RuleImpl;
import ru.sysoevm.carservise.rule.statechange.Service2RuleImpl;
import ru.sysoevm.carservise.rule.statechange.Service3RuleImpl;
import ru.sysoevm.carservise.service.CarServiceRuleHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateStateProcess {

    @Autowired
    CarServiceRuleHandler ruleHandler;

    // Если надо добавить различные обработчики, например обработчик который заносит запись для саппорта (чтобы обзвонили)
    //@Autowired
    //SupportRuleHandler supportRuleHandler;

    @Scheduled(cron = "0 05 16 * * *")
    public void start() {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Service1RuleImpl());
        rules.add(new Service2RuleImpl());
        rules.add(new Service3RuleImpl());
        rules.add(new ClosedRuleImpl());
        update(rules);
    }

    public void update(List<Rule> rules) {
        for (Rule rule : rules) {
            ruleHandler.handle(rule);
        }
    }
}
