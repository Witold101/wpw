package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
// Класс родитель для всех записей в табицах БД

import java.util.Date;

public class TableItem {
    private int id;
    private Date dateLastChanges;

    public TableItem( Date date){
        dateLastChanges=date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Date getDateLastChanges() {
        return dateLastChanges;
    }

    public void setDateLastChanges(Date dateLastChanges) {
        this.dateLastChanges = dateLastChanges;
    }
}