package com.app.boneappleteeth;

import java.util.List;

public class ResepModel {
    private int id_menu;
    private String nama;
    private String foto;
    private int waktu;
    private int kesulitan;
    private String resep;
    private List<BahanModel> bahan;
    private float rating;

    @Override
    public String toString() {
        return "ResepModel{" +
                "id_menu=" + id_menu +
                ", nama='" + nama + '\'' +
                ", foto='" + foto + '\'' +
                ", waktu=" + waktu +
                ", kesulitan=" + kesulitan +
                ", resep='" + resep + '\'' +
                ", bahan=" + bahan +
                ", rating=" + rating +
                ", rating_count=" + rating_count +
                ", author='" + author + '\'' +
                '}';
    }

    private int rating_count;
    private String author;

    public ResepModel(int id_menu, String nama, String foto, int waktu, int kesulitan, String resep, List<BahanModel> bahan, float rating, int rating_count, String author) {
        this.id_menu = id_menu;
        this.nama = nama;
        this.foto = foto;
        this.waktu = waktu;
        this.kesulitan = kesulitan;
        this.resep = resep;
        this.bahan = bahan;
        this.rating = rating;
        this.rating_count = rating_count;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
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

    public List<BahanModel> getBahan() {
        return bahan;
    }

    public void setBahan(List<BahanModel> bahan) {
        this.bahan = bahan;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }
}
