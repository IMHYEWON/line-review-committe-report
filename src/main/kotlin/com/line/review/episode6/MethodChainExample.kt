package com.line.review.episode6

/**
 * 메서드 체인/폴백 체인 예제 (Kotlin)
 */
class MethodChainExample {
    
    /**
     * Bad 버전: 메서드 체인에서 부적절한 줄바꿈
     */
    fun processCollectionBad(items: List<Any>): Set<String> {
        // Bad: 메서드 체인 중간에서 줄바꿈
        return items.filterIsInstance<String>()
                .map { it.uppercase() }
                .filter { it.length > 3 }
                .toSet()
    }
    
    /**
     * Good 버전: 메서드 체인에서 연산자 앞에서 줄바꿈
     */
    fun processCollectionGood(items: List<Any>): Set<String> {
        // Good: 연산자 앞에서 줄바꿈하여 구조와 흐름을 명확히
        return items.filterIsInstance<String>()
            .map { it.uppercase() }
            .filter { it.length > 3 }
            .toSet()
    }
    
    /**
     * Bad 버전: 폴백 체인에서 부적절한 줄바꿈
     */
    fun getValueBad(nullable: String?, fallback: String?, another: String?): String {
        // Bad: 폴백 체인 중간에서 줄바꿈
        val result = nullable ?: 
                fallback ?: another
        return result ?: "default"
    }
    
    /**
     * Good 버전: 폴백 체인에서 연산자 앞에서 줄바꿈
     */
    fun getValueGood(nullable: String?, fallback: String?, another: String?): String {
        // Good: 엘비스 연산자(?:) 앞에서 줄바꿈하여 폴백 구조를 명확히
        return nullable
            ?: fallback
            ?: another
            ?: "default"
    }
    
    /**
     * Good 버전: 보조 함수를 사용하여 인수가 긴 경우 처리
     */
    fun getValueWithHelper(nullable: String?, fallback: Fallback?): String {
        // Good: 보조 함수를 사용하여 줄바꿈 위치 조정
        return nullable
            ?: fallback?.shortcut
            ?: "default"
    }
}

/**
 * 보조 함수 예제를 위한 데이터 클래스
 */
data class Fallback(val value: String) {
    val shortcut: String
        get() = value.withLongLongLongLongLongLongLongArgument("parameter")
    
    private fun String.withLongLongLongLongLongLongLongArgument(param: String): String {
        return "$this-$param"
    }
}

