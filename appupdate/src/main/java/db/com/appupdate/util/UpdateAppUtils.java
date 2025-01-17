package bd.com.appupdate.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import bd.com.appupdate.R;
import bd.com.appupdate.customview.AppUpdateDialog;
import bd.com.appupdate.customview.ConfirmDialog;
import bd.com.appupdate.feature.Callback;


public class UpdateAppUtils {

    private OnCancelClicked onCancelClicked;
    private final String TAG = "UpdateAppUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;

    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    // private int serverVersionCode = 0;
    private String apkPath = "";
    private String serverVersionName = "";
    private boolean isForce = false; //
    private int localVersionCode = 0;
    private String localVersionName = "";
    public static boolean needFitAndroidN = true; // 7.0 false
    public static boolean showNotification = true;
    private String updateInfo = "";
    public static String apkDigest;

    public UpdateAppUtils needFitAndroidN(boolean needFitAndroidN) {
        UpdateAppUtils.needFitAndroidN = needFitAndroidN;
        return this;
    }    

    public UpdateAppUtils setOnCancelClicked(OnCancelClicked onCancelClicked) {
        this.onCancelClicked = onCancelClicked;
        return this;
    }

    private UpdateAppUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
    }

    public static UpdateAppUtils from(Activity activity) {
        return new UpdateAppUtils(activity);
    }

    public UpdateAppUtils checkBy(int checkBy) {
        this.checkBy = checkBy;
        return this;
    }

    public UpdateAppUtils apkPath(String apkPath) {
        this.apkPath = apkPath;
        return this;
    }

    public UpdateAppUtils apkDigest(String apkDigest) {
        this.apkDigest = apkDigest;
        return this;
    }

    public UpdateAppUtils downloadBy(int downloadBy) {
        this.downloadBy = downloadBy;
        return this;
    }

    public UpdateAppUtils showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    public UpdateAppUtils updateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
        return this;
    }

    public UpdateAppUtils serverVersionName(String serverVersionName) {
        this.serverVersionName = serverVersionName;
        return this;
    }

    public UpdateAppUtils isForce(boolean isForce) {
        this.isForce = isForce;
        showNotification = !isForce;
        return this;
    }

    //apk currentVersionCode
    private void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 
            localVersionCode = info.versionCode; // 
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void toUpdate() {

        realUpdate();

    }

    private void realUpdate() {
        AppUpdateDialog dialog = new AppUpdateDialog(activity, isForce ? R.layout.dialog_app_must_update : R.layout.dialog_app_update);
        dialog.content(updateInfo, serverVersionName, apkPath);
        dialog.setCancelable(!isForce);
        dialog.isForce(isForce);
        dialog.show();
//        ConfirmDialog dialog = new ConfirmDialog(activity, new Callback() {
//            @Override
//            public void callback(int position) {
//                switch (position) {
//                    case 0:  //cancle
//                        if (onCancelClicked != null) {
//                            onCancelClicked.canceled(isForce);
//                        }
//                        if (isForce) System.exit(0);
//                        break;
//
//                    case 1:  //sure
//                        if (downloadBy == DOWNLOAD_BY_APP) {
//                            if (isWifiConnected(activity)) {
////                                DownloadAppUtils.downloadForAutoInstall(activity, apkPath, "demo.apk", serverVersionName);
//                                DownloadAppUtils.download(activity, apkPath, serverVersionName);
//                            } else {
//                                new ConfirmDialog(activity, new Callback() {
//                                    @Override
//                                    public void callback(int position) {
//                                        if (position == 1) {
//                                            DownloadAppUtils.download(activity, apkPath, serverVersionName);
//                                            //DownloadAppUtils.downloadForAutoInstall(activity, apkPath, "demo.apk", serverVersionName);
//                                        } else {
//                                            if (isForce) activity.finish();
//                                        }
//                                    }
//                                }).setContent("WiFi\n？").show();
//                            }
//
//                        } else if (downloadBy == DOWNLOAD_BY_BROWSER) {
//                            DownloadAppUtils.downloadForWebView(activity, apkPath);
//                        }
//                        break;
//                }
//            }
//        });
//
//        String content = ":" + serverVersionName + "\n?";
//        if (!TextUtils.isEmpty(updateInfo)) {
//            content = ":" + serverVersionName + "?\n\n" + updateInfo;
//        }
//        dialog.setContent(content);
//        dialog.setCancelable(false);
//        dialog.show();
    }


    /**
     * wifi
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    public interface OnCancelClicked {
        void canceled(boolean force);
    }
}
