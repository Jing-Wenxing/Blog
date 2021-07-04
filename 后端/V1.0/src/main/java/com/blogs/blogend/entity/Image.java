package com.blogs.blogend.entity;

public class Image {
    String id;
    String bucketname;
    String objectname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", bucketname='" + bucketname + '\'' +
                ", objectname='" + objectname + '\'' +
                '}';
    }
}
