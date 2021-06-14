package ru.sysoevm.carservise.rule;

import ru.sysoevm.carservise.rule.statechange.CarServiceStateEnum;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Генерирует полную строку запроса query, создаёт список параметров params и их типов argTypes.
 */
public class ParamImpl {

    private Rule rule;

    public ParamImpl(Rule rule) {
        this.rule = rule;
    }

    /**
     * Полная строка запроса
     */
    private String query;

    /**
     * Список параметров, который будет передаватсья в запрос.
     */
    private List<Object> params = new ArrayList<>();

    /**
     * Список типов аргументов Date, Date, Varchar в кодах типа int 92, 92, 12.
     */
    private List<Integer> argTypes = new ArrayList<>();

    /**
     * Базовая строка из которой формируется основная строка запроса query.
     */
    private static String BASE_QUERY = "SELECT id, car_model_id, car_vin_code, purchase_datetime, buyer_name, buyer_phone, service_state " +
            " FROM sold_car_service " +
            " WHERE service_state != 'CLOSED' ";

    public List<Object> getParams() {
        return params;
    }

    public int[] getArgTypes() {
        int[] argTypesArr = new int[argTypes.size()];
        for (int i = 0; i < argTypes.size(); i++) {
            argTypesArr[i] = argTypes.get(i);
        }
        return argTypesArr;
    }

    public String getQuery() {
        return query;
    }

    /**
     * Создание параметров для будущего запроса.
     */
    public void creatingParameters() {
        query = BASE_QUERY;

        Date from = rule.getFrom();
        if (from != null) {
            query += " AND purchase_datetime < ? ";
            params.add(from);
            argTypes.add(Types.DATE);
        }

        Date to = rule.getTo();
        if (to != null) {
            query += " AND purchase_datetime >= ? ";
            params.add(to);
            argTypes.add(Types.DATE);
        }

        CarServiceStateEnum state = rule.getState();

        if (state==null) {
            throw new NoSuchElementException("Состояния для сервиса не существует.");
        }

        query += " AND service_state != ? ";
        params.add(state);
        argTypes.add(Types.VARCHAR);
    }
}
