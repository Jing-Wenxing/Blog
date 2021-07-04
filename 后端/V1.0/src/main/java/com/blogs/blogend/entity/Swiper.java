package com.blogs.blogend.entity;

public class Swiper {
    String id;
    String title;
    String text;
    String img;
    String swquence;
    Boolean isshow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSwquence() {
        return swquence;
    }

    public void setSwquence(String swquence) {
        this.swquence = swquence;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    @Override
    public String toString() {
        return "Swiper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", swquence='" + swquence + '\'' +
                ", isshow=" + isshow +
                '}';
    }
}
