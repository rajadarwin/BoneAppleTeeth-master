package com.app.boneappleteeth;

public class BahanModel {
    private int id_bahan;
    private String nama;
    private String gambar;
    static private String BASE_URL;

    public BahanModel(int id_bahan, String nama, String gambar) {
        this.id_bahan = id_bahan;
        this.nama = nama;
        this.gambar = gambar;
    }

    public int getId_bahan() {
        return id_bahan;
    }

    public void setId_bahan(int id_bahan) {
        this.id_bahan = id_bahan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
