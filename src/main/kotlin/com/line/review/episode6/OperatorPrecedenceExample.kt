package com.line.review.episode6

/**
 * 연산자 우선순위 예제 (Kotlin)
 */
class OperatorPrecedenceExample {
    
    private val valueWithLongName1 = 10
    private val valueWithLongName2 = 5
    private val valueWithLongName3 = 3
    private val valueWithLongName4 = 2
    
    /**
     * Bad 버전: 연산자 우선순위를 고려하지 않은 줄바꿈
     */
    fun compareBad(): Boolean {
        // Bad: + 나 - 에서 줄바꿈하여 연산자 우선순위가 불명확
        return valueWithLongName1 -
                valueWithLongName2 == valueWithLongName3 +
                valueWithLongName4
    }
    
    /**
     * Good 버전: 연산자 우선순위를 고려한 줄바꿈
     */
    fun compareGood(): Boolean {
        // Good: == 에서 줄바꿈하여 비교 연산이 명확해짐
        return valueWithLongName1 - valueWithLongName2 ==
                valueWithLongName3 + valueWithLongName4
    }
    
    /**
     * Good 버전: 괄호를 사용하여 연산자 우선순위를 명확히
     */
    fun calculateWithParentheses(): Int {
        // Good: 괄호로 묶인 부분의 연결성을 강조
        return valueWithLongName1 *
                (valueWithLongName2 + valueWithLongName3)
    }
}

