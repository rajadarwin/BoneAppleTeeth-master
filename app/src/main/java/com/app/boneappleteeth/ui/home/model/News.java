package com.app.boneappleteeth.ui.home.model;

public class News {
    private int id;
    private int imagePath;
    private String text;
    private String nama;
    private String isi;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
