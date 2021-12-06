package com.app.boneappleteeth;

public class NewRecipeModel {
    String nama;
    String waktu;
    int kesulitan;
    String resep;
    String author;

    public NewRecipeModel(String nama, String waktu, int kesulitan, String resep, String author) {
        this.nama = nama;
        this.waktu = waktu;
        this.kesulitan = kesulitan;
        this.resep = resep;
        this.author = author;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getKesulitan() {
        return kesulitan;
    }

    public void setKesulitan(int kesulitan) {
        this.kesulitan = kesulitan;
    }

    public String getResep() {
        return resep;
    }

    public void setResep(String resep) {
        this.resep = resep;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
