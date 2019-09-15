package com.example.mrsfulogoff;

public class Consult_message {
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SENT=1;
    private String content;
    private int type;

    public Consult_message(String content, int type) {
        this.content=content;
        this.type=type;
    }
    public String getContent() {
        return content;
    }
    public int getType() {
        return type;
    }
}
