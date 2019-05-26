package com.apibatdongsan.batdongsandanang.exception;

public class BatDongSanException extends RuntimeException{
    public BatDongSanException(String message) {
        super(message);
    }

    public BatDongSanException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
