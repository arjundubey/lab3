package com.lab3;

public class Greeting {

    private final long id;
    private final String content;
    private String age;

    public Greeting(long id, String content,String age) {
        this.id = id;
        this.content = content;
        this.age = age;
    }

    public long getId() {
        return id;
    }
    
    public String getAge() {
        return age;
    }
    
    public String getContent() {
        return content;
    }

}