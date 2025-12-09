package com.line.review.episode23;

import java.util.List;

/**
 * 빈 컬렉션 순회 예제
 * 
 * map, filter, forEach 등 컬렉션을 순회하는 고차 함수는
 * 많은 언어와 라이브러리에서 컬렉션이 비어 있을 때에도 잘 작동합니다.
 * 또한 sum이나 reduce와 같은 폴딩을 수행하는 함수도
 * 컬렉션이 비어 있을 때에도 자연스럽게 작동하는 경우가 많습니다.
 */
public class EmptyCollectionExample {
    
    /**
     * Bad 버전: 빈 컬렉션에 대한 조기 반환
     */
    public int processNumbersBad(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
    
    /**
     * Good 버전: 빈 컬렉션도 정상 케이스로 처리
     * 
     * sum()은 빈 리스트에 대해 자연스럽게 0을 반환
     */
    public int processNumbersGood(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
    
    /**
     * Bad 버전: allMatch에 대한 조기 반환
     */
    public boolean checkAllPositiveBad(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return true; // 빈 리스트는 모든 조건을 만족한다고 간주
        }
        return numbers.stream().allMatch(n -> n > 0);
    }
    
    /**
     * Good 버전: allMatch는 빈 리스트에 대해 true를 반환
     */
    public boolean checkAllPositiveGood(List<Integer> numbers) {
        return numbers.stream().allMatch(n -> n > 0);
    }
    
    /**
     * Bad 버전: anyMatch에 대한 조기 반환
     */
    public boolean checkAnyPositiveBad(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return false; // 빈 리스트는 어떤 조건도 만족하지 않는다고 간주
        }
        return numbers.stream().anyMatch(n -> n > 0);
    }
    
    /**
     * Good 버전: anyMatch는 빈 리스트에 대해 false를 반환
     */
    public boolean checkAnyPositiveGood(List<Integer> numbers) {
        return numbers.stream().anyMatch(n -> n > 0);
    }
}

