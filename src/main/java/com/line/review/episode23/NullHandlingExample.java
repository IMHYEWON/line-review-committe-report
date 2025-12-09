package com.line.review.episode23;

import java.util.Optional;

/**
 * null 처리 예제
 * 
 * Java에서는 Optional을 사용하여 null을 정상 케이스로 취급할 수 있습니다.
 */
public class NullHandlingExample {
    
    /**
     * Bad 버전: null에 대한 조기 반환
     */
    public String convertBad(String value) {
        if (value == null) {
            return null;
        }
        return value.toUpperCase();
    }
    
    /**
     * Good 버전: Optional을 사용하여 null 처리
     */
    public String convertGood(String value) {
        return Optional.ofNullable(value)
                .map(String::toUpperCase)
                .orElse(null);
    }
    
    /**
     * Bad 버전: null에 대한 조기 반환과 기본값
     */
    public int getLengthBad(String value) {
        if (value == null) {
            return 0;
        }
        return value.length();
    }
    
    /**
     * Good 버전: Optional로 기본값 제공
     */
    public int getLengthGood(String value) {
        return Optional.ofNullable(value)
                .map(String::length)
                .orElse(0);
    }
    
    /**
     * Good 버전: null 체크 후 직접 처리
     */
    public int getLengthWithNullCheck(String value) {
        return value != null ? value.length() : 0;
    }
}

