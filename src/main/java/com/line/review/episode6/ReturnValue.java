package com.line.review.episode6;

/**
 * 반환값을 나타내는 클래스
 */
public class ReturnValue {
    private final String value;
    
    public ReturnValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "ReturnValue{value='" + value + "'}";
    }
}

