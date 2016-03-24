package com.vutuanchien.android_chap14_webviewnewspaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterListview extends ArrayAdapter {
    List<News> newsList;
    Context context;
    List<News> mDataListOriginal;

    public AdapterListview(Context context, int resource, List<News> newsList) {
        super(context, resource, newsList);
        this.context = context;
        this.newsList = newsList;
        mDataListOriginal = new ArrayList<>(newsList);
        mDataListOriginal.addAll(newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        URL url;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customlistview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tieude.setText(newsList.get(position).getTitle());
        viewHolder.ngayviet.setText(newsList.get(position).getPubDate());
        if (newsList.get(position).getDescription().isEmpty()) {
            Picasso.with(context).load(newsList.get(position).getLinkImage()).into(viewHolder.hinhdaidien);
        } else {
            Picasso.with(context).load(getImage(newsList.get(position).getDescription())).into(viewHolder.hinhdaidien);
        }
        return convertView;
    }

    private String getImage(String description) {
        int a = description.indexOf("src=");
        int start = description.indexOf("\"", a);
        int end = description.indexOf("\"", start + 1);
        Log.d("a", a + "");
        Log.d("start", start + "");
        Log.d("end", end + "");
        String image = "";
        if ((start != -1) && (end != -1)) {
            image = description.substring(start + 1, end);
        }
        Log.d("Image", image);
        return image;
    }
}
