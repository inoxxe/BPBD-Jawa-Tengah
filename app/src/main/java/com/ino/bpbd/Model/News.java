package com.ino.bpbd.Model;

public class News {

    private int id;
    private String judul;
    private String description;
    private String isi;
    private String tag;
    private String tanggal;
    private String gambar;
    private String gambarDefault;
//    private int gambar; //jika gambar dari drawable
    //private String author;

    public News(int id, String judul, String description, String isi,String tag, String tanggal, String gambar,String gambarDefault){
        this.id = id;
        this.judul = judul;
        this.description = description;
        this.isi = isi;
        this.tag = tag;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.gambarDefault = gambarDefault;
        //this.author = author;
    }

    public News(){

    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getDescription(){
        return description;
    }

    public String getIsi() {
        return isi;
    }

    public String getTag() {
        return tag;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public String getGambarDefault(){
        return gambarDefault;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setGambarDefault(String gambarDefault){
        this.gambarDefault = gambarDefault;
    }
}
