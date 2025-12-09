package com.line.review.episode6;

/**
 * 연산자 우선순위 예제
 * 
 * 대부분의 경우 연산자 우선순위는 의미 연결의 강도에 따라 결정됩니다.
 * == 에서 줄을 바꾸는 것이 + 나 - 에서 줄을 바꾸는 것보다 이해하기 쉬운 코드가 됩니다.
 */
public class OperatorPrecedenceExample {
    
    private int valueWithLongName1 = 10;
    private int valueWithLongName2 = 5;
    private int valueWithLongName3 = 3;
    private int valueWithLongName4 = 2;
    
    /**
     * Bad 버전: 연산자 우선순위를 고려하지 않은 줄바꿈
     * 
     * + 나 - 에서 줄을 바꾸면 연산자 우선순위가 명확하지 않음
     */
    public boolean compareBad() {
        // Bad: + 나 - 에서 줄바꿈하여 연산자 우선순위가 불명확
        boolean result = valueWithLongName1 -
                valueWithLongName2 == valueWithLongName3 +
                valueWithLongName4;
        return result;
    }
    
    /**
     * Good 버전: 연산자 우선순위를 고려한 줄바꿈
     * 
     * == 에서 줄을 바꾸면 비교 연산이 명확해짐
     */
    public boolean compareGood() {
        // Good: == 에서 줄바꿈하여 비교 연산이 명확해짐
        boolean result = valueWithLongName1 - valueWithLongName2 ==
                valueWithLongName3 + valueWithLongName4;
        return result;
    }
    
    /**
     * Good 버전: 괄호를 사용하여 연산자 우선순위를 명확히
     * 
     * () 로 묶인 부분의 연결성이 더 강함
     */
    public int calculateWithParentheses() {
        // Good: 괄호로 묶인 부분의 연결성을 강조
        int result = valueWithLongName1 *
                (valueWithLongName2 + valueWithLongName3);
        return result;
    }
    
    /**
     * Bad 버전: 괄호 내부에서 부적절한 줄바꿈
     */
    public int calculateWithParenthesesBad() {
        // Bad: 괄호 내부에서 줄바꿈하여 연결성이 약해짐
        int result = valueWithLongName1 * (
                valueWithLongName2 + valueWithLongName3);
        return result;
    }
}

