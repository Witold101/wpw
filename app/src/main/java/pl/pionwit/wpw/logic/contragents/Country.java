package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.Date;

public class Country extends TableItem implements ITableAll {
    private String name;
    private int kod;
    private String litlCod;

    public Country(String name, int kod,Date date,String litlCod, int id) {
        super(date);
        super.setId(id);
        this.name = name;
        this.kod = kod;
        this.litlCod=litlCod;
    }

    public Country(){
        super(new Date());
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

    public String getLitlCod() {
        return litlCod;
    }

    public void setLitlCod(String litlCod) {
        this.litlCod = litlCod;
    }

    @Override
    public void setDate(Date date) {
        setDateLastChanges(date);
    }
}
