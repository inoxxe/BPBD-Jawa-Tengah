package com.ino.bpbd.Model;

public class Category {

    private String title;
    private int gambar;


    public Category(String title, int gambar) {
        this.title = title;
        this.gambar = gambar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
