package bd.com.bdwallet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bd.com.bdwallet.R;


public class CommonItemView extends RelativeLayout {

    private TextView mDesTv;
    private TextView mValueTv;

    private ImageView mIcon;
    private ImageView mRightImageView;

    private String des;
    private String vaue;
    private boolean showLeftIcon;
    private boolean showRightIcon;
    private boolean showValRightIcon;
    
}
