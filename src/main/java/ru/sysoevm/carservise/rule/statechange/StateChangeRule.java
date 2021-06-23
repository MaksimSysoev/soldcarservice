package ru.sysoevm.carservise.rule.statechange;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.Rule;
import ru.sysoevm.carservise.utils.DateUtils;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StateChangeRule implements Rule {

    private Date from;
    private Date to;
    private CarServiceStateEnum state;


    public StateChangeRule(Date from, Date to, CarServiceStateEnum state) {
        this.from = from;
        this.to = to;
        this.state = state;
    }

    private Date getFrom() {
        if (from == null) {
            return DateUtils.minDate();
        }
        return from;
    }

    private Date getTo() {
        if (to == null) {
            return DateUtils.maxDate();
        }
        return to;
    }

    private CarServiceStateEnum getState() {
        return state;
    }

    @Override
    public boolean validate() {

        if (to == null && from == null) {
            return false;
        }

        if (state == null) {
            return false;
        }

        return true;
    }

    @Override
    public void apply(JdbcTemplate jdbcTemplate) {
        String query = "UPDATE sold_car_service SET service_state = ? WHERE service_state != 'CLOSED' AND service_state != ? AND purchase_datetime >= ? AND purchase_datetime < ?";
        jdbcTemplate.update(query,  getState().toString(), getState().toString(), getFrom(), getTo());

    }
}
