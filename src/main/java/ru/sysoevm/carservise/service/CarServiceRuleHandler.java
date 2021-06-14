package ru.sysoevm.carservise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sysoevm.carservise.data.SoldCarServiceDTO;
import ru.sysoevm.carservise.rule.statechange.CarServiceStateEnum;
import ru.sysoevm.carservise.rule.Rule;

@Component
public class CarServiceRuleHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void handle(Rule rule) {
        rule.handle(jdbcTemplate).forEach(soldCarServiceDTO -> {
            updateStatus(soldCarServiceDTO, rule.getState());
        });
    }


    /**
     * Обновление статуса у SoldCarService.
     * В случае ошибки создаётся запись в тадлице error_log.
     * @param soldCarServiceDTO
     * @param state
     */
    private void updateStatus(SoldCarServiceDTO soldCarServiceDTO, CarServiceStateEnum state) {
        try {
            jdbcTemplate.update("UPDATE sold_car_service SET service_state = ? WHERE id = ?", state.toString(), soldCarServiceDTO.getId());
        } catch (Exception e) {
            jdbcTemplate.update("INSERT INTO error_log VALUES(?, ?)", soldCarServiceDTO.getPurchase_datetime(), e.getMessage());
        }
    }


}
