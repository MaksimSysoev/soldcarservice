package ru.sysoevm.carservise.process;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.*;
import ru.sysoevm.carservise.rule.statechange.*;
import ru.sysoevm.carservise.utils.DateUtils;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class UpdateStateProcess {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 14 13 * * *")
    public void start() {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Service1Rule());
        rules.add(new Service2Rule());
        rules.add(new Service3Rule());
        rules.add(new ClosedRule());
        update(rules);
    }


    public void update(List<Rule> rules) {

        List<Object[]> errorParams = new ArrayList<>();
        Date now = DateUtils.now();

        try {
            for (Rule rule : rules) {
                if (rule.validate()) {
                    rule.apply(jdbcTemplate);
                }
            }
        } catch (Exception e) {
            errorParams.add(new Object[] {now, e.getMessage()});
        }

        if (errorParams.size() > 0) {
            String insertErrorsQuery = "INSERT INTO error_log VALUES(?, ?)";
            try {
                jdbcTemplate.batchUpdate(insertErrorsQuery, errorParams, new int[] {Types.DATE, Types.VARCHAR});
            } catch (Exception e) {

            }
        }



    }

}
