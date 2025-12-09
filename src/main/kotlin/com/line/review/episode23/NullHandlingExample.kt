package com.line.review.episode23

/**
 * null 처리 예제
 * 
 * 언어에 따라서는 세이프 콜 연산자 ?.라고 불리는 연산자가 있는데요.
 * null(null, undefined)을 정상 케이스로 취급하고자 할 때 이 연산자가 유용합니다.
 */
class NullHandlingExample {
    
    /**
     * Bad 버전: null에 대한 조기 반환
     */
    fun convertBad(value: String?): String? {
        if (value == null) {
            return null
        }
        return value.uppercase()
    }
    
    /**
     * Good 버전: 세이프 콜 연산자 사용
     */
    fun convertGood(value: String?): String? =
        value?.uppercase()
    
    /**
     * Bad 버전: null에 대한 조기 반환과 기본값
     */
    fun getLengthBad(value: String?): Int {
        if (value == null) {
            return 0
        }
        return value.length
    }
    
    /**
     * Good 버전: 엘비스 연산자로 기본값 제공
     */
    fun getLengthGood(value: String?): Int =
        value?.length ?: 0
    
    /**
     * Good 버전: orEmpty() 사용
     */
    fun getLengthWithOrEmpty(value: String?): Int =
        value.orEmpty().length
}

