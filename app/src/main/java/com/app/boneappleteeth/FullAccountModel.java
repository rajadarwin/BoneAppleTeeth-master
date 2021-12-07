package com.app.boneappleteeth;

public class FullAccountModel {
    String username;
    String email;
    String password;
    String foto;

    @Override
    public String toString() {
        return "FullAccountModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", foto='" + foto + '\'' +
                ", alamat='" + alamat + '\'' +
                ", user_key='" + user_key + '\'' +
                '}';
    }

    String alamat;
    String user_key;

    public FullAccountModel(String username, String email, String password, String foto, String alamat, String user_key) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.alamat = alamat;
        this.user_key = user_key;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
}
