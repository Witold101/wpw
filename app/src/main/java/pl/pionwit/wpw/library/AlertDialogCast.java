package pl.pionwit.wpw.library;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Witold on 25.12.2015.
 */
public class AlertDialogCast {

    private boolean show;
    private Context context;
    private View view;
    private TextView[] textViews;

    public AlertDialogCast(boolean show, View view) {
        this.show = show;
        this.context = view.getContext();
        this.view = view;
    }

    public void setTextViews(TextView[] textViews){
        this.textViews=textViews;
    }
}
