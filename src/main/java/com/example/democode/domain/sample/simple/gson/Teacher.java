package com.example.democode.domain.sample.simple.gson;

import com.google.gson.annotations.Expose;

public class Teacher {
    //private transient int id;
    @Expose(serialize = false, deserialize = true)
    private int id;

    @Expose(serialize = true, deserialize = false)
    private String name;

    private String major;

    public Teacher(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", major=" + major + ", name=" + name + "]";
    }
}
