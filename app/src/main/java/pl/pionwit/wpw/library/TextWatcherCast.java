package pl.pionwit.wpw.library;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Witold on 23.12.2015.
 */
public class TextWatcherCast implements TextWatcher {

    private EditText editText;
    private int maxLength;
    private int minLength;
    private boolean upperCase;
    private boolean checkStringToInt;
    private boolean flag;

    public TextWatcherCast(EditText et){
        super();
        editText=et;
        maxLength=-1;
        minLength=1;
        upperCase=false;
        checkStringToInt=false;
        if (et.getText().length()==0) {
            editText.setError("Строка пуста!");
            flag=false;
        }else {
            flag=true;
        }
    }

    public void afterTextChanged(Editable s) {

        if (checkStringToInt){
            if (editText.getText().toString().matches("^-?\\d+$")){
                flag=true;
            }else {
                flag=false;
                editText.setError("Код должен состоять из цифр!");
                return;
            }
        }

        if(upperCase){
            if (!editText.getText().toString().toUpperCase()
                    .equals(editText.getText().toString())){
                flag=false;
                editText.setError("Текст не в верхнем регистре!");
                return;
            }else{
                flag=true;
            }
        }

        if (maxLength>0) {
            if (editText.getText().toString().length() > maxLength) {
                flag=false;
                editText.setError("Длина текста больше " + maxLength + " символов!");
                return;
            }else{
                flag=true;
            }
        }
        if (minLength>=0) {
            if (editText.getText().toString().length() < minLength) {
                flag=false;
                editText.setError("Длина текста меньше " + minLength + " символов!");
                return;
            }else {
                flag = true;
            }
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    // Установка максимальной длины строки
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    // Установка минимальной длины строки
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    // Флаг результата проверки
    public boolean isFlag() {
        return flag;
    }

    // Установка на проверку на верхний регистр
    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

    public void setCheckStringToInt(boolean checkStringToInt) {
        this.checkStringToInt = checkStringToInt;
    }
}
