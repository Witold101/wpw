package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class Mail extends TableItem {
    private String mail;
    private int contactId;


    public Mail(Date date) {
        super(date);
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    @Override
    public void setDateChanges(Date date) {
        super.setDateChanges(date);
    }
}

