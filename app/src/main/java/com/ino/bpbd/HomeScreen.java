package com.ino.bpbd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    public PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        getSupportActionBar().setTitle("Home");
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Setting Notifikasi Terlebih dahulu dengan menekan tombol atur notifikasi dibawah!");
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    prefManager.setFirstTimeLaunch(false);
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
    }

    }

    public void berita(View view) {
        Intent i = new Intent(getApplicationContext(),NewsActivity.class);
        startActivity(i);
    }

    public void ppid(View view) {
        Intent ppid = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ppid);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public void notifikasi(View view) {
        Intent ppid = new Intent(getApplicationContext(),NotificationActivity.class);
        startActivity(ppid);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
