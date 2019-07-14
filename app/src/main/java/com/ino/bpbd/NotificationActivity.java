package com.ino.bpbd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox semarang,banjarnegara,batang,blora,boyolali,brebes,kendal,
    cilacap,demak,jepara,kajen,karanganyar,kebumen,klaten,magelang,mungkid,
    pekalongan,salatiga,surakarta,tegal,kudus,pati,pemalang,purbalingga,
    purwodadi,purwokerto,purworejo,rembang,slawi,sragen,sukoharjo,temanggung,
    wonogiri,wonosobo,ungaran;
    private static final String TAG = "NotificationActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        getSupportActionBar().setTitle("Pilihan Kota");


        semarang = (CheckBox)findViewById(R.id.chk_semarang);
        semarang.setOnClickListener(this);
        banjarnegara = (CheckBox)findViewById(R.id.chk_banjarnegara);
        banjarnegara.setOnClickListener(this);
        batang = (CheckBox)findViewById(R.id.chk_batang);
        batang.setOnClickListener(this);
        blora = (CheckBox)findViewById(R.id.chk_blora);
        blora.setOnClickListener(this);
        boyolali = (CheckBox)findViewById(R.id.chk_boyolali);
        boyolali.setOnClickListener(this);
        brebes = (CheckBox)findViewById(R.id.chk_brebes);
        brebes.setOnClickListener(this);
        cilacap = (CheckBox)findViewById(R.id.chk_cilacap);
        cilacap.setOnClickListener(this);
        demak = (CheckBox)findViewById(R.id.chk_Demak);
        demak.setOnClickListener(this);
        jepara = (CheckBox)findViewById(R.id.chk_jepara);
        jepara.setOnClickListener(this);
        kajen = (CheckBox)findViewById(R.id.chk_kajen);
        kajen.setOnClickListener(this);
        kendal = (CheckBox)findViewById(R.id.chk_kendal);
        kendal.setOnClickListener(this);
        karanganyar = (CheckBox)findViewById(R.id.chk_karanganyar);
        karanganyar.setOnClickListener(this);
        kebumen = (CheckBox)findViewById(R.id.chk_kebumen);
        kebumen.setOnClickListener(this);
        klaten = (CheckBox)findViewById(R.id.chk_klaten);
        klaten.setOnClickListener(this);
        magelang= (CheckBox)findViewById(R.id.chk_magelang);
        magelang.setOnClickListener(this);
        mungkid = (CheckBox)findViewById(R.id.chk_mungkid);
        mungkid.setOnClickListener(this);
        pekalongan = (CheckBox)findViewById(R.id.chk_pekalongan);
        pekalongan.setOnClickListener(this);
        salatiga = (CheckBox)findViewById(R.id.chk_salatiga);
        salatiga.setOnClickListener(this);
        surakarta = (CheckBox)findViewById(R.id.chk_surakarta);
        surakarta.setOnClickListener(this);
        tegal = (CheckBox)findViewById(R.id.chk_tegal);
        tegal.setOnClickListener(this);
        kudus = (CheckBox)findViewById(R.id.chk_kudus);
        kudus.setOnClickListener(this);
        pati = (CheckBox)findViewById(R.id.chk_pati);
        pati.setOnClickListener(this);
        pemalang = (CheckBox)findViewById(R.id.chk_pemalang);
        pemalang.setOnClickListener(this);
        purbalingga = (CheckBox)findViewById(R.id.chk_purbalingga);
        purbalingga.setOnClickListener(this);
        purwodadi = (CheckBox)findViewById(R.id.chk_purwodadi);
        purwodadi.setOnClickListener(this);
        purwokerto = (CheckBox)findViewById(R.id.chk_purwokerto);
        purwokerto.setOnClickListener(this);
        purworejo = (CheckBox)findViewById(R.id.chk_purworejo);
        purworejo.setOnClickListener(this);
        rembang = (CheckBox)findViewById(R.id.chk_rembang);
        rembang.setOnClickListener(this);
        slawi = (CheckBox)findViewById(R.id.chk_slawi);
        slawi.setOnClickListener(this);
        sragen = (CheckBox)findViewById(R.id.chk_sragen);
        sragen.setOnClickListener(this);
        sukoharjo = (CheckBox)findViewById(R.id.chk_sukoharjo);
        sukoharjo.setOnClickListener(this);
        temanggung = (CheckBox)findViewById(R.id.chk_temanggung);
        temanggung.setOnClickListener(this);
        wonogiri = (CheckBox)findViewById(R.id.chk_wonogiri);
        wonogiri.setOnClickListener(this);
        wonosobo = (CheckBox)findViewById(R.id.chk_wonosobo);
        wonosobo.setOnClickListener(this);
        ungaran = (CheckBox)findViewById(R.id.chk_ungaran);
        ungaran.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chk_semarang:
                if(semarang.isChecked()){
                    subscribe("semarang");
                }else{
                    unsubscribe("semarang");

                }
                break;
            case R.id.chk_banjarnegara:
                if(banjarnegara.isChecked()){
                    subscribe("banjarnegara");
                }else{
                    unsubscribe("banjarnegara");

                }
                break;
            case R.id.chk_batang:
                if(batang.isChecked()){
                    subscribe("batang");
                }else{
                    unsubscribe("batang");

                }
                break;
            case R.id.chk_blora:
                if(blora.isChecked()){
                    subscribe("blora");
                }else{
                    unsubscribe("blora");

                }
                break;
            case R.id.chk_boyolali:
                if(boyolali.isChecked()){
                    subscribe("boyolali");
                }else{
                    unsubscribe("boyolali");

                }
                break;
            case R.id.chk_brebes:
                if(brebes.isChecked()){
                    subscribe("brebes");
                }else{
                    unsubscribe("brebes");

                }
                break;
            case R.id.chk_cilacap:
                if(cilacap.isChecked()){
                    subscribe("cilacap");
                }else{
                    unsubscribe("cilacap");

                }
                break;
            case R.id.chk_Demak:
                if(demak.isChecked()){
                    subscribe("demak");
                }else{
                    unsubscribe("demak");

                }
                break;
            case R.id.chk_jepara:
                if(jepara.isChecked()){
                    subscribe("jepara");
                }else{
                    unsubscribe("jepara");

                }
                break;
            case R.id.chk_kajen:
                if(kajen.isChecked()){
                    subscribe("kajen");
                }else{
                    unsubscribe("kajen");

                }
                break;
            case R.id.chk_kendal:
                if(kendal.isChecked()){
                    subscribe("kendal");
                }else{
                    unsubscribe("kendal");

                }
                break;
            case R.id.chk_karanganyar:
                if(karanganyar.isChecked()){
                    subscribe("karanganyar");
                }else{
                    unsubscribe("karanganyar");

                }
                break;
            case R.id.chk_kebumen:
                if(kebumen.isChecked()){
                    subscribe("kebumen");
                }else{
                    unsubscribe("kebumen");

                }
                break;
            case R.id.chk_klaten:
                if(klaten.isChecked()){
                    subscribe("klaten");
                }else{
                    unsubscribe("klaten");

                }
                break;
            case R.id.chk_magelang:
                if(magelang.isChecked()){
                    subscribe("magelang");
                }else{
                    unsubscribe("magelang");

                }
                break;
            case R.id.chk_mungkid:
                if(mungkid.isChecked()){
                    subscribe("mungkid");
                }else{
                    unsubscribe("mungkid");

                }
                break;
            case R.id.chk_pekalongan:
                if(pekalongan.isChecked()){
                    subscribe("pekalongan");
                }else{
                    unsubscribe("pekalongan");

                }
                break;
            case R.id.chk_salatiga:
                if(salatiga.isChecked()){
                    subscribe("saltiga");
                }else{
                    unsubscribe("salatiga");

                }
                break;
            case R.id.chk_surakarta:
                if(surakarta.isChecked()){
                    subscribe("surakarta");
                }else{
                    unsubscribe("surakarta");

                }
                break;
            case R.id.chk_tegal:
                if(tegal.isChecked()){
                    subscribe("tegal");
                }else{
                    unsubscribe("tegal");

                }
                break;
            case R.id.chk_kudus:
                if(kudus.isChecked()){
                    subscribe("kudus");
                }else{
                    unsubscribe("kudus");

                }
                break;
            case R.id.chk_pati:
                if(pati.isChecked()){
                    subscribe("pati");
                }else{
                    unsubscribe("pati");

                }
                break;
            case R.id.chk_pemalang:
                if(pemalang.isChecked()){
                    subscribe("pemalang");
                }else{
                    unsubscribe("pemalang");

                }
                break;
            case R.id.chk_purbalingga:
                if(purbalingga.isChecked()){
                    subscribe("purbalingga");
                }else{
                    unsubscribe("purbalingga");

                }
                break;
            case R.id.chk_purwodadi:
                if(purwodadi.isChecked()){
                    subscribe("purwodadi");
                }else{
                    unsubscribe("purwodadi");

                }
                break;
            case R.id.chk_purwokerto:
                if(purwokerto.isChecked()){
                    subscribe("purwokerto");
                }else{
                    unsubscribe("purwokerto");

                }
                break;
            case R.id.chk_purworejo:
                if(purworejo.isChecked()){
                    subscribe("purworejo");
                }else{
                    unsubscribe("purworejo");

                }
                break;
            case R.id.chk_rembang:
                if(rembang.isChecked()){
                    subscribe("rembang");
                }else{
                    unsubscribe("rembang");

                }
                break;
            case R.id.chk_slawi:
                if(slawi.isChecked()){
                    subscribe("slawi");
                }else{
                    unsubscribe("slawi");

                }
                break;
            case R.id.chk_sragen:
                if(sragen.isChecked()){
                    subscribe("sragen");
                }else{
                    unsubscribe("sragen");

                }
                break;
            case R.id.chk_sukoharjo:
                if(sukoharjo.isChecked()){
                    subscribe("sukoharjo");
                }else{
                    unsubscribe("sukoharjo");

                }
                break;
            case R.id.chk_temanggung:
                if(temanggung.isChecked()){
                    subscribe("temanggung");
                }else{
                    unsubscribe("temanggung");

                }
                break;
            case R.id.chk_wonogiri:
                if(wonogiri.isChecked()){
                    subscribe("wonogiri");
                }else{
                    unsubscribe("wonogiri");

                }
                break;
            case R.id.chk_wonosobo:
                if(wonosobo.isChecked()){
                    subscribe("wonosobo");
                }else{
                    unsubscribe("wonosobo");

                }
                break;
            case R.id.chk_ungaran:
                if(ungaran.isChecked()){
                    subscribe("ungaran");
                }else{
                    unsubscribe("ungaran");
                }
                break;
        }
    }

    public void subscribe(String kota){
        final String topik = kota;
        Log.d(TAG, "Subscribing to "+topik+" topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(topik)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subsribed to "+topik +" topic";
                        if (!task.isSuccessful()) {
                            msg = "Failed to subsribed to "+topik +" topic";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(NotificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void unsubscribe(String kota){
       final String topik2 = kota;
        Log.d(TAG, "Unsubsribe to "+topik2+" topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topik2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Unsubsribed to "+topik2 +" topic";
                        if (!task.isSuccessful()) {
                            msg = "Failed to unsubsribed to "+topik2 +" topic";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(NotificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
