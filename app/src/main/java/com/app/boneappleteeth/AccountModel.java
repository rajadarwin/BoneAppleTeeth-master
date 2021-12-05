package com.app.boneappleteeth;

public class AccountModel {
    String username;
    String email;
    String password;
    String foto;
    String alamat;

    public AccountModel(String username, String email, String password, String foto, String alamat) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
