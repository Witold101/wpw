package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
// Класс родитель для всех записей в табицах БД

import java.util.Date;

public class TableItem {
    private long _id;
    private Date dateChanges;

    public TableItem( Date date){
        dateChanges=date;
    }


    public long getId() {
        return _id;
    }

    public void setId(long id){
        this._id=id;
    }

    public Date getDateChanges() {
        return dateChanges;
    }

    public void setDateChanges(Date date) {
        this.dateChanges = date;
    }
}