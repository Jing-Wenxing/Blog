package com.blogs.blogend.entity;

public class Artcle {
    String id;
    String title;
    String timeline;
    String summary;
    String text;
    String classification;
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

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    @Override
    public String toString() {
        return "Artcle{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", timeline='" + timeline + '\'' +
                ", summary='" + summary + '\'' +
                ", text='" + text + '\'' +
                ", classification='" + classification + '\'' +
                ", isshow=" + isshow +
                '}';
    }
}
