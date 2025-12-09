package com.line.review.episode6

/**
 * 엘비스 리턴(?: return) 예제 (Kotlin)
 */
class ElvisReturnExample {
    
    /**
     * Bad 버전: 엘비스 리턴에서 부적절한 줄바꿈
     * 
     * return 이 오른쪽에 있어서 강조되지 않음
     */
    fun processValueBad(nullable: String?, parameter: String): String {
        // Bad: return이 오른쪽에 있어서 강조되지 않음
        val nonNullValue = nullable?.plus(parameter) ?: return "default"
        return nonNullValue
    }
    
    /**
     * Good 버전: 엘비스 리턴에서 연산자 앞에서 줄바꿈
     * 
     * return 을 왼쪽에 배치하여 강조
     */
    fun processValueGood(nullable: String?, parameter: String): String {
        // Good: return을 왼쪽에 배치하여 강조
        val nonNullValue = nullable?.plus(parameter)
            ?: return "default"
        
        // snip...
        // snip...
        
        return nonNullValue
    }
    
    /**
     * Bad 버전: 복잡한 조건에서 return이 숨겨짐
     */
    fun processComplexBad(value: String?, fallback: String?, another: String?): String {
        // Bad: return이 복잡한 조건문 안에 숨겨져 있음
        val result = value ?: 
                fallback ?: 
                another ?: return "default"
        return result
    }
    
    /**
     * Good 버전: 복잡한 조건에서 early return으로 명확하게
     */
    fun processComplexGood(value: String?, fallback: String?, another: String?): String {
        // Good: early return으로 null 체크를 명확하게
        return value
            ?: fallback
            ?: another
            ?: return "default"
    }
}

