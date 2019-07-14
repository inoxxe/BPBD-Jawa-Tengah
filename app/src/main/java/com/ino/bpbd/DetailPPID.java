package com.ino.bpbd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailPPID extends AppCompatActivity {

    private TextView textViewPejabat;
    private TextView textViewPenanggung;
    private TextView textViewWaktu;
    private TextView textViewFormat;
    private TextView textViewJangkaWaktu;
    private TextView textViewRingkasan;
    private TextView textViewKeterangan;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String judul,kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailppid);
        Intent intent = getIntent();

        judul = intent.getStringExtra(Config.JUDUL);
        getSupportActionBar().setTitle(judul);
        textViewPejabat = (TextView) findViewById(R.id.info_pejabat);
        textViewPenanggung = (TextView) findViewById(R.id.info_penanggung);
        textViewWaktu = (TextView) findViewById(R.id.info_waktu);
        textViewFormat = (TextView) findViewById(R.id.info_format);
        textViewJangkaWaktu = (TextView) findViewById(R.id.info_jangkawaktu);
        textViewRingkasan = (TextView) findViewById(R.id.info_ringkasan);
        textViewKeterangan = (TextView) findViewById(R.id.info_ket);


        getDetail();
    }

    private void getDetail() {
        class getDetail extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPPID.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                ReqHandler rh = new ReqHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_DETAIL, judul);
                return s;
            }
        }
        getDetail gd = new getDetail();
        gd.execute();
    }

    private void showDetail(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String pejabat = c.getString(Config.TAG_PEJABAT);
            String penanggung = c.getString(Config.TAG_PENANGGUNG);
            String waktu = c.getString(Config.TAG_WAKTU);
            String format = c.getString(Config.TAG_FORMAT);
            String jangka = c.getString(Config.TAG_JANGKA);
            String ringkasan = c.getString(Config.TAG_RINGKASAN);
            String keterangan = c.getString(Config.TAG_KETERANGAN);

            if(!pejabat.equals("null")){
                textViewPejabat.setText(pejabat);
            }
            if(!penanggung.equals("null")){
                textViewPenanggung.setText(penanggung);
            }
            if(!waktu.equals("null")){
                textViewWaktu.setText(waktu);
            }
            if(!format.equals("null")){
                textViewFormat.setText(format);
            }
            if(!jangka.equals("null")){
                textViewJangkaWaktu.setText(jangka);
            }
            if(!ringkasan.equals("null")){
                textViewRingkasan.setText(ringkasan);
            }
            if(!keterangan.equals("null")){
                textViewKeterangan.setText(keterangan);
            }







        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
