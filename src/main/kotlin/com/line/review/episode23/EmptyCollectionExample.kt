package com.line.review.episode23

/**
 * 빈 컬렉션 순회 예제
 * 
 * map, filter, forEach 등 컬렉션을 순회하는 고차 함수는
 * 많은 언어와 라이브러리에서 컬렉션이 비어 있을 때에도 잘 작동합니다.
 * 또한 sum이나 reduce와 같은 폴딩을 수행하는 함수도
 * 컬렉션이 비어 있을 때에도 자연스럽게 작동하는 경우가 많습니다.
 */
class EmptyCollectionExample {
    
    /**
     * Bad 버전: 빈 컬렉션에 대한 조기 반환
     */
    fun processNumbersBad(numbers: List<Int>): Int {
        if (numbers.isEmpty()) {
            return 0
        }
        return numbers.sum()
    }
    
    /**
     * Good 버전: 빈 컬렉션도 정상 케이스로 처리
     * 
     * sum()은 빈 리스트에 대해 자연스럽게 0을 반환
     */
    fun processNumbersGood(numbers: List<Int>): Int {
        return numbers.sum()
    }
    
    /**
     * Bad 버전: all, any에 대한 조기 반환
     */
    fun checkAllPositiveBad(numbers: List<Int>): Boolean {
        if (numbers.isEmpty()) {
            return true // 빈 리스트는 모든 조건을 만족한다고 간주
        }
        return numbers.all { it > 0 }
    }
    
    /**
     * Good 버전: all은 빈 리스트에 대해 true를 반환
     */
    fun checkAllPositiveGood(numbers: List<Int>): Boolean {
        return numbers.all { it > 0 }
    }
    
    /**
     * Bad 버전: any에 대한 조기 반환
     */
    fun checkAnyPositiveBad(numbers: List<Int>): Boolean {
        if (numbers.isEmpty()) {
            return false // 빈 리스트는 어떤 조건도 만족하지 않는다고 간주
        }
        return numbers.any { it > 0 }
    }
    
    /**
     * Good 버전: any는 빈 리스트에 대해 false를 반환
     */
    fun checkAnyPositiveGood(numbers: List<Int>): Boolean {
        return numbers.any { it > 0 }
    }
}

