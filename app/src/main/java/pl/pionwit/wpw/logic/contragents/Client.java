package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.ArrayList;
import java.util.Date;


public class Client extends TableItem implements ITableAll {
    private String name;
    private String fullName;
    private String web;
    private ArrayList<Note> notes;
    private ArrayList<ContactItem> contacts;

    public Client(Date date, String name, String fullName,String web,
                  ArrayList<Note> notes, ArrayList<ContactItem> contacts) {
        super(date);
        this.name = name;
        this.fullName = fullName;
        this.web=web;
        this.notes = new ArrayList<>(notes);
        this.contacts = new ArrayList<>(contacts);
    }

    @Override
    public void setDate(Date date) {
        setDateLastChanges(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<ContactItem> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactItem> contacts) {
        this.contacts = contacts;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}

