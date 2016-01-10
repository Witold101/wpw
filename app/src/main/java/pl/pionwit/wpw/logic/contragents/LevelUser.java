package pl.pionwit.wpw.logic.contragents;

import java.util.Date;

/**
 * Created by Witold on 10.01.2016.
 */
public class LevelUser extends TableItem {

    private int number;
    private String note;

    public LevelUser(int number, String note, Date date) {
        super(date);
        this.number=number;
        this.note=note;
    }
    public LevelUser(int number, String note, Date date, long _id) {
        super(date);
        this.number=number;
        this.note=note;
        super.setId(_id);
    }

    public int getNumber() {
        return number;
    }

    public void setNamber(int namber) {
        this.number = namber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
