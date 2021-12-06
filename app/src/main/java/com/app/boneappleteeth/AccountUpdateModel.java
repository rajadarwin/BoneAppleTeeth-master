package com.app.boneappleteeth;

public class AccountUpdateModel {
    String username;
    String email;
    String alamat;

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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public AccountUpdateModel(String username, String email, String alamat) {
        this.username = username;
        this.email = email;
        this.alamat = alamat;
    }
}
