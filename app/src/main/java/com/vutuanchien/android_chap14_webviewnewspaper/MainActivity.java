package com.vutuanchien.android_chap14_webviewnewspaper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAsyntaskReadNews myAsyntaskReadNews;
    WifiNetworkReceiver networkReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager manager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            myAsyntaskReadNews = new MyAsyntaskReadNews();
            try {
//            get and execute this link to show
                myAsyntaskReadNews.execute(new URL("http://vnexpress.net/rss/tin-moi-nhat.rss"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            NetConnectionDialog();
        }
        networkReceiver = new WifiNetworkReceiver();
        listView = (ListView) findViewById(R.id.listView);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ShowAlertDialog();
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    void ShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát khỏi ứng dụng không ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    void NetConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Vui Lòng Kiểm Tra Kết Nối Mạng");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }

    // thread run in background of app
    public class MyAsyntaskReadNews extends AsyncTask<URL, Void, List<News>> {
        AdapterListview adapterListview;

        @Override
        protected List<News> doInBackground(URL... params) {
            for (URL url : params) {
                try {
                    URLConnection connection = url.openConnection();
                    if (url.getPath().contains("HTTPS")) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
                        if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK) {
                            return RSSUtils.read24hcom(httpURLConnection.getInputStream());
                        } else {

                        }
                    } else {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
                        if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK) {
                            return RSSUtils.read24hcom(httpURLConnection.getInputStream());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<News> newses) {
            adapterListview = new AdapterListview(MainActivity.this, R.layout.customlistview, newses);
            listView.setAdapter(adapterListview);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, WebViewReadNews.class);
                    intent.putExtra("link", newses.get(position).getLink());
                    startActivity(intent);
                }
            });
        }
    }
}
