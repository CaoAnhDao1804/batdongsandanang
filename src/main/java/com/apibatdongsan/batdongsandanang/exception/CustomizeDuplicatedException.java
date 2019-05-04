package com.apibatdongsan.batdongsandanang.exception;

public class CustomizeDuplicatedException extends Exception {
    private String nameColumn;

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public CustomizeDuplicatedException(String message, String name) {
        super(message);
        this.nameColumn = name;
    }
}
