package pl.pionwit.wpw.logic.settings;

/**
 * Created by Witold on 21.12.2015.
 */
public class SettingsItem
{
    private int id;
    private int idImg;
    private String nameMenu;

    public SettingsItem(int id, int idImg, String nameMenu) {
        this.id = id;
        this.idImg = idImg;
        this.nameMenu = nameMenu;
    }

    public int getId() {
        return id;
    }

    public int getIdImg() {
        return idImg;
    }

    public String getNameMenu() {
        return nameMenu;
    }
}
