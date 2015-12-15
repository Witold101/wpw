package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

// Интерфейс описывающий поведение таблицы БД

public interface ITableDB <T> {
    void add(T table);
    boolean dell(int id);
    boolean edit(T table);
    void setDateTimeTable(Date dateTime, int id);
    T getItem(int id);
}

