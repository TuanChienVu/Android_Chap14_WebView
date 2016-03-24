package com.vutuanchien.android_chap14_webviewnewspaper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver {

    private Context context;

    public NetworkReceiver(Context context) {
        this.context = context;
    }

    public boolean checkMobileInternetConn() {

        //Tạo đối tương ConnectivityManager để trả về thông tin mạng
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Nếu đối tượng khác null
        if (connectivity != null) {
            //Nhận thông tin mạng
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                //Tìm kiếm thiết bị đã kết nối được internet chưa
                if (info.isConnected()) {
                    return true;
                }
            }

        }
        return false;
    }
}
