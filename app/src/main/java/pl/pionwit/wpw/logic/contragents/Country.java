package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class Country extends TableItem implements ITableAll {
    private String name;
    private int kod;

    public Country(String name, int kod,Date date) {
        super(date);
        this.name = name;
        this.kod = kod;
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

    @Override
    public void setDate(Date date) {
        setDateLastChanges(date);
    }
}
