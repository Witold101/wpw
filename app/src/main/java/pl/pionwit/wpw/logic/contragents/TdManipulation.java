package pl.pionwit.wpw.logic.contragents;

/**
 * Created by Witold on 07.12.2015.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс оббертка для работы с таблицами базы данных
 */
public class TdManipulation <T extends ITableAll> implements ITableDB  {
    private ArrayList<T> tables;

    public TdManipulation(ArrayList<T> tables){
        if (this.tables == null) {
            this.tables = new ArrayList<>(tables);
        }
        else
        {
            this.tables=tables;}
    }


    @Override
    public void add(Object table) {
        this.tables.add((T)table);
    }

    @Override
    public boolean dell(int id) {
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getId()==id){
                tables.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean edit(Object table){
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getId() == ((T)table).getId()) {
                tables.set(i,((T)table));
                return true;
            }
        }
        return false;
    }


    @Override
    public void setDateTimeTable(Date dateTime,int id) {
        getItem(id).setDate(dateTime);
    }

    @Override
    public T getItem(int id) {
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getId() == id) {
                return tables.get(i);
            }
        }
        return null;
    }

    public ArrayList<T> getTables() {
        return tables;
    }
}
