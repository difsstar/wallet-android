package bd.com.bdwallet.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import bd.com.bdwallet.R;


public class MessageDialog extends Dialog {

    private TextView titleTv;
    private TextView desTv;
    private TextView accountTv;
    private Context mContext;
    private TextView cancelTv;
    private TextView okTv;
    private OnOkClickListener listener;
    private String pwdStr;    

    public MessageDialog(@NonNull Context context) {
        super(context, R.style.DialogTranslucentNoTitle);
        mContext = context;
        setContentView(R.layout.dialog_messsage_layout);
        cancelTv = (TextView) findViewById(R.id.cancel_tv);
        okTv = (TextView) findViewById(R.id.ok_tv);
        setCanceledOnTouchOutside(false);
        titleTv = (TextView) findViewById(R.id.title_tv);
        desTv = (TextView) findViewById(R.id.title_des_tv);
        accountTv = (TextView) findViewById(R.id.account_name_tv);
        setListener();

    }

    private void setListener() {
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onOkClick(pwdStr);
                }
                dismiss();
            }
        });

    }

    public MessageDialog setTitle(String title) {
        titleTv.setText(title);
        return this;
    }

    public MessageDialog setDes(String des) {
        desTv.setText(des);
        return this;
    }
}