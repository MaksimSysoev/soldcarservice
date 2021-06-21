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

    public Date getFrom() {
        if (from == null) {
            return DateUtils.minDate();
        }
        return from;
    }

    public Date getTo() {
        if (to == null) {
            return DateUtils.maxDate();
        }
        return to;
    }

    public CarServiceStateEnum getState() {
        return state;
    }

    public StateChangeRule  setFrom(Date from) {
        this.from = from;
        return this;
    }

    public StateChangeRule setTo(Date to) {
        this.to = to;
        return this;
    }

    public StateChangeRule setState(CarServiceStateEnum state) {
        this.state = state;
        return this;
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

        List<Object[]> errorParams = new ArrayList<>();

        Date now = DateUtils.now();

        try {
            String updateQuery="UPDATE sold_car_service SET service_state = '" + getState().toString() + "' WHERE id = ? ";

            List<SoldCarServiceDTO> purchases = jdbcTemplate.query("SELECT id " +
                            " FROM sold_car_service WHERE service_state != ? " +
                            " AND service_state != 'CLOSED' " +
                            "AND purchase_datetime >= ? " +
                            "AND purchase_datetime < ? ",
                    new Object[] {getState().toString(), getFrom(), getTo()},
                    new int[] { Types.VARCHAR, Types.DATE, Types.DATE },
                    new BeanPropertyRowMapper<>(SoldCarServiceDTO.class));

            List<Object[]> updateParams = new ArrayList<>();
            purchases.forEach(dto -> updateParams.add(new Object[]{dto.getId()}));

            int[] result = jdbcTemplate.batchUpdate(updateQuery, updateParams, new int[] { Types.INTEGER });

            SoldCarServiceDTO notUpdatedDTO = null;
            for (int i = 0; i < result.length; i++) {
                if (result[i] == 0) {
                    notUpdatedDTO = purchases.get(i);
                    errorParams.add(new Object[] {now, "Not updated, id = " + notUpdatedDTO.getId()});
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
