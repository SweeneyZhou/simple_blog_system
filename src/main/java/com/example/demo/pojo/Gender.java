package com.example.demo.pojo;

public enum Gender {
    MALE(0, "男"), FEMALE(1, "女USER");
    private int key;
    private String value;

    /**
     * @param key
     * @param value
     */
    private Gender(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
