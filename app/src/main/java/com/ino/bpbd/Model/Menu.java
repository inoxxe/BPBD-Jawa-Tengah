package com.ino.bpbd.Model;

public class Menu {
    int gambar;
    String judul;

    public Menu(int gambar, String judul) {
        this.gambar = gambar;
        this.judul = judul;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
