package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.ArrayList;
import java.util.Date;

public class ContactItem extends TableItem {
    private Date createDate;
    private String name;
    private ArrayList<Phone> phones;
    private ArrayList<Mail> email;
    private int contragentId;
    private String department;
    private String note;


    public ContactItem(Date createDate,String name, int contragentId, String department, String note)
    {
        super(createDate);
        //this.phones=new ArrayList<>(phones);
        this.createDate = createDate;
        this.name = name;
        //this.email = new ArrayList<>(email);
        this.contragentId = contragentId;
        this.department = department;
        this.note = note;
    }

    public void setDate(Date date) {
        setDateChanges(date);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }

    public ArrayList<Mail> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<Mail> email) {
        this.email = email;
    }

    public int getContragentId() {
        return contragentId;
    }

    public void setContragentId(int contragentId) {
        this.contragentId = contragentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
