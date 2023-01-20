package org.example.entities;

public class Movie {
    private String title;
    private boolean isViewed;

    public Movie() {
    }

    public Movie(String title, boolean isViewed) {
        this.title = title;
        this.isViewed = isViewed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", isViewed=" + isViewed +
                '}';
    }
}