package com.deskads.dto;


import javax.validation.constraints.Size;

/**
 * Created by Administrator on 05.11.2017.
 */
public class AnnouncementForm {
    private String id;


    @Size(min=10, max=100, message = "Заголовок объявления должен содержать от 10 до 100 символов")
    private String title;
    @Size(min=1, max=400, message = "Содержание объявления не должно быть пустым и не должно содержать более 400 символов")
    private String text;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
