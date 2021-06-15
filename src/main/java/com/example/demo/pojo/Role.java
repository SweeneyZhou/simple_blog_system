package com.example.demo.pojo;

public enum Role {
    ADMIN(0, "ADMIN"), USER(1, "USER");
    private int key;
    private String name;

    /**
     * @param key
     * @param name
     */
    private Role(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
