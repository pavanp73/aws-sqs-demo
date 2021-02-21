package com.demo.aws.sqs;

public class Payload {

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
