package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class Country extends TableItem implements ITableAll {
    private String name;
    private int kod;
    private int idImgFlag;

    public Country(String name, int kod,Date date,int idImgFlag) {
        super(date);
        this.name = name;
        this.kod = kod;
        this.idImgFlag=idImgFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getIdImgFlag() {
        return idImgFlag;
    }

    public void setIdImgFlag(int idImgFlag) {
        this.idImgFlag = idImgFlag;
    }

    @Override
    public void setDate(Date date) {
        setDateLastChanges(date);
    }
}
