package com.myjingdong001.vo;

public class ListViewCars {
    private Integer id;
    private String url;
    private String title;
    private String message;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ListViewCars{" + "id=" + id + ", url='" + url + '\'' + ", title='" + title + '\'' + ", message='" + message + '\'' + '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
