package com.app.boneappleteeth.ui.favorite.model;

public class Favorite {
    private int id;
    private int imagePath;
    private String title;
    private String menit;
    private boolean isFavorite;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", imagePath=" + imagePath +
                ", title='" + title + '\'' +
                ", menit='" + menit + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenit() {
        return menit;
    }

    public void setMenit(String menit) {
        this.menit = menit;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
