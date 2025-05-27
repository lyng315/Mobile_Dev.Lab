package com.example.lab24;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lvTigia;
    TextView txtdate;
    ArrayList<Tygia> dstygia;
    MyArrayAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTigia = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);
        getdate();
        dstygia = new ArrayList<Tygia>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.item, dstygia);
        lvTigia.setAdapter(myadapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();
    }

    public void getdate() {
        // Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        // Format theo định dạng dd/MM/yyyy
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        // Hiển thị lên textview
        txtdate.setText("Hôm Nay: " + simpleDate.format(currentDate));
    }

    class TyGiaTask extends AsyncTask<Void, Void, ArrayList<Tygia>> {
        @Override
        protected ArrayList<Tygia> doInBackground(Void... voids) {
            ArrayList<Tygia> ds = new ArrayList<>();
            try {
                // Đọc JSON từ file assets/tygia.json
                InputStream is = getAssets().open("tygia.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String json = new String(buffer, "UTF-8");

                // Log thử JSON
                Log.d("PHAN_HOI", json);

                // Parse JSON
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    Tygia tiGia = new Tygia();
                    tiGia.setType(item.getString("type"));

                    if (item.has("imageurl")) {
                        tiGia.setImageurl(item.getString("imageurl"));
                        try {
                            URL url = new URL(tiGia.getImageurl());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
                            connection.setRequestProperty("Accept", "*/*");
                            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                            tiGia.setBitmap(bitmap);
                            connection.disconnect();
                        } catch (Exception e) {
                            Log.e("Lỗi tải ảnh", e.toString());
                        }
                    }
                    if (item.has("muatienmat")) {
                        tiGia.setMuatienmat(item.getString("muatienmat"));
                    }
                    if (item.has("muack")) {
                        tiGia.setMuack(item.getString("muack"));
                    }
                    if (item.has("bantienmat")) {
                        tiGia.setBantienmat(item.getString("bantienmat"));
                    }
                    if (item.has("banck")) {
                        tiGia.setBanck(item.getString("banck"));
                    }
                    ds.add(tiGia);
                }

            } catch (Exception ex) {
                Log.e("Lỗi đọc JSON", ex.toString());
            }
            return ds;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Tygia> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
            myadapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
