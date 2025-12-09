package com.line.review.episode23;

import java.util.List;

/**
 * 범위를 벗어난 배열이나 리스트 예제
 * 
 * 인덱스의 범위를 조사하는 코드가 있다면
 * 범위를 벗어났을 때 조기 반환하는 것보다
 * 범위 체크를 포함한 메서드를 사용하는 게 좋습니다.
 */
public class IndexRangeExample {
    
    private final List<String> fooList = List.of("foo1", "foo2", "foo3");
    
    /**
     * Bad 버전: 인덱스 범위 체크 후 조기 반환
     */
    public String getFooBad(int index) {
        if (index < 0 || fooList.size() <= index) {
            return null;
        }
        return fooList.get(index);
    }
    
    /**
     * Good 버전: 범위 체크를 포함한 메서드 사용
     */
    public String getFooGood(int index) {
        if (index >= 0 && index < fooList.size()) {
            return fooList.get(index);
        }
        return null;
    }
    
    /**
     * Good 버전: 유틸리티 메서드 사용
     */
    public String getFooWithUtil(int index) {
        return getOrNull(fooList, index);
    }
    
    /**
     * 범용적인 getOrNull 메서드
     */
    private <T> T getOrNull(List<T> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }
    
    /**
     * Bad 버전: 인덱스 범위 체크 후 기본값 반환
     */
    public String getFooWithDefaultBad(int index) {
        if (index < 0 || fooList.size() <= index) {
            return "default";
        }
        return fooList.get(index);
    }
    
    /**
     * Good 버전: 범위 체크와 기본값을 함께 처리
     */
    public String getFooWithDefaultGood(int index) {
        if (index >= 0 && index < fooList.size()) {
            return fooList.get(index);
        }
        return "default";
    }
}

