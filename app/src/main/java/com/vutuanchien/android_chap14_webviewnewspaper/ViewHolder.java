package com.vutuanchien.android_chap14_webviewnewspaper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    //lop quan ly cac member view trong convertview
    TextView tieude;
    TextView ngayviet;
    ImageView hinhdaidien;

    ViewHolder(View rootView) {
        tieude = (TextView) rootView.findViewById(R.id.tv_Tieude);
        ngayviet = (TextView) rootView.findViewById(R.id.tv_ngayviet);
        hinhdaidien = (ImageView) rootView.findViewById(R.id.hinhdaidien);
    }
}