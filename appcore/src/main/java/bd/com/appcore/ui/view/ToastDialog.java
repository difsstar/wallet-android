package bd.com.appcore.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import bd.com.appcore.CoreApp;
import bd.com.appcore.R;


public class ToastDialog extends Dialog {

    private View v;
    private TextView tipTextView;
    private ImageView img;
    private TextView desTv;
    private Context context;
    private AVLoadingIndicatorView loadingView;   

    public ToastDialog(Context context) {
        super(context);
    }
}
