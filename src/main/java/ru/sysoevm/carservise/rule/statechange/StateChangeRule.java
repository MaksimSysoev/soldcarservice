package ru.sysoevm.carservise.rule.statechange;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.ParamImpl;
import ru.sysoevm.carservise.rule.Rule;

import java.util.List;

public abstract class StateChangeRule implements Rule {

    public List<SoldCarServiceDTO> handle(JdbcTemplate jdbcTemplate) {

        ParamImpl param = new ParamImpl(this);
        param.creatingParameters();

        return jdbcTemplate.query(
                param.getQuery(),
                param.getParams().toArray(),
                param.getArgTypes(),
                new BeanPropertyRowMapper<>(SoldCarServiceDTO.class)
        );

    }
}
