package pl.pionwit.wpw.logic.contragents;

import java.util.Date;

/**
 * Created by Witold on 04.01.2016.
 */
public class Category {
    private long _id;
    private String name;
    private Date date_changes;

    public Category(String name, Date date_changes, long _id) {
        this.name = name;
        this.date_changes = date_changes;
        this._id=_id;
    }

    public Category(String name, Date date_changes) {
        this.name = name;
        this.date_changes = date_changes;
    }

    public long get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Date getDate_changes() {
        return date_changes;
    }

    public void setName(String name) {
        this.name = name;
    }
}

