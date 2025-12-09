package com.line.review.episode6;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 메서드 체인/폴백 체인 예제
 * 
 * 도트 연산자(.)나 세이프 콜 연산자(?.) 등을 이용한 메서드 체인 또는
 * 엘비스 연산자(?:) 등을 이용한 폴백(fallback) 체인을 사용하는 경우,
 * 코드의 세부적인 부분보다 로직의 구조와 흐름이 더 중요한 경우가 많습니다.
 * 이러한 경우는 . , ?. , ?: 연산자 바로 앞에 줄 바꿈을 넣는 것이 좋습니다.
 */
public class MethodChainExample {
    
    
    /**
     * Bad 버전: 폴백 체인에서 부적절한 줄바꿈
     */
    public String getValueBad(String nullable, String fallback, String another) {
        // Bad: 폴백 체인 중간에서 줄바꿈
        String result = nullable != null ? nullable : 
                fallback != null ? fallback : another;
        return result;
    }
    
    /**
     * Good 버전: 폴백 체인에서 연산자 앞에서 줄바꿈
     */
    public String getValueGood(String nullable, String fallback, String another) {
        // Good: 엘비스 연산자(?:) 앞에서 줄바꿈하여 폴백 구조를 명확히
        // Java에서는 삼항 연산자를 사용하지만, Kotlin의 ?: 패턴을 모방
        String result = nullable != null ? nullable
                : fallback != null ? fallback
                : another;
        return result;
    }
    
    /**
     * Good 버전: Optional을 사용한 폴백 체인 (Java 스타일)
     */
    public String getValueWithOptional(String nullable, String fallback, String another) {
        // Good: Optional을 사용하여 폴백 체인을 명확하게 표현
        return java.util.Optional.ofNullable(nullable)
                .or(() -> java.util.Optional.ofNullable(fallback))
                .or(() -> java.util.Optional.ofNullable(another))
                .orElse("default");
    }
}

