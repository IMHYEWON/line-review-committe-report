package com.line.review.episode23

/**
 * 범위를 벗어난 배열이나 리스트 예제
 * 
 * 인덱스의 범위를 조사하는 코드가 있다면
 * 범위를 벗어났을 때 조기 반환하는 것보다
 * getOrNull이나 getOrElse와 같은 함수를 사용하는 게 좋습니다.
 */
class IndexRangeExample {
    
    private val fooList: List<String> = listOf("foo1", "foo2", "foo3")
    
    /**
     * Bad 버전: 인덱스 범위 체크 후 조기 반환
     */
    fun getFooBad(index: Int): String? {
        if (index < 0 || fooList.size <= index) {
            return null
        }
        return fooList[index]
    }
    
    /**
     * Good 버전: getOrNull 사용
     */
    fun getFooGood(index: Int): String? =
        fooList.getOrNull(index)
    
    /**
     * Bad 버전: 인덱스 범위 체크 후 기본값 반환
     */
    fun getFooWithDefaultBad(index: Int): String {
        if (index < 0 || fooList.size <= index) {
            return "default"
        }
        return fooList[index]
    }
    
    /**
     * Good 버전: getOrElse 사용
     */
    fun getFooWithDefaultGood(index: Int): String =
        fooList.getOrElse(index) { "default" }
}

