package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

// Интерфейс описывающий поведение объекта одной записи в таблице бд

public interface ITableAll {
    int getId();
    void setDate(Date date);
}
