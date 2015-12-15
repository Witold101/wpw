package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class Note extends TableItem implements ITableAll {

    private String note;
    private int clientId;

    public Note(String note, int clientId, Date date) {
        super(date);
        this.note=note;
        this.clientId=clientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setDate(Date date) {

    }
}

