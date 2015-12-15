package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class ContragentItem {
    private int id;
    private String contragent;
    private String contact;
    private String phone;
    private Boolean select;
    private int imgIcon;

    public ContragentItem(String contragent,String contact, String phone,
                          int imgIcon, Boolean select) {
        this.contragent = contragent;
        this.phone = phone;
        this.select = select;
        this.imgIcon=imgIcon;
        this.contact=contact;
    }

    public String getContact() {
        return contact;
    }

    public String getContragent() {
        return contragent;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getSelect() {
        return select;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}
