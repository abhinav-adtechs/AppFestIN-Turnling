package com.winuall.turnling;


public class MessageFormat {

    private String text ;
    private String target_id ;
    private String user_id ;

    public MessageFormat() {
    }

    public MessageFormat(String text, String target_id, String user_id) {
        this.text = text;
        this.target_id = target_id;
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
