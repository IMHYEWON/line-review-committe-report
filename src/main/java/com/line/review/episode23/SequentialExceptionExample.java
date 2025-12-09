package com.line.review.episode23;

import java.util.Optional;
import java.util.function.Function;

/**
 * 연속 함수 호출 중 예외 예제
 * 
 * 함수 중 여러 곳에서 예외가 발생하는 경우
 * 조기 반환을 적용하려고 하면 코드가 복잡해질 수 있습니다.
 * 이런 경우에는 Optional이나 Result 타입을 이용하면
 * 더 명확하게 만들 수 있습니다.
 */
sealed interface FooResult<T> permits FooResult.Success, FooResult.Error {
    record Success<T>(T value) implements FooResult<T> {}
    record Error<T>(ErrorType errorType) implements FooResult<T> {}
    
    @SuppressWarnings("unchecked")
    default <U> FooResult<U> flatMap(Function<T, FooResult<U>> action) {
        if (this instanceof Success<T> success) {
            return action.apply(success.value);
        } else if (this instanceof Error<?> error) {
            return (FooResult<U>) new Error<U>(error.errorType);
        }
        throw new IllegalStateException("Unexpected result type");
    }
}

enum ErrorType {
    SOME, ANOTHER, YET_ANOTHER
}

record SomeData(String value) {}
record AnotherData(String value) {}
record YetAnotherData(String value) {}
record FooData(String value) {}

public class SequentialExceptionExample {
    
    private final ApiClient apiClient = new ApiClient();
    private final UnreliableRepository unreliableRepository = new UnreliableRepository();
    
    /**
     * Bad 버전: 여러 곳에서 조기 반환으로 예외 처리
     * 
     * 문제점:
     * 1. 각 단계마다 try-catch와 조기 반환 반복
     * 2. 코드가 복잡하고 읽기 어려움
     * 3. 에러 처리와 정상 흐름이 섞여 있음
     */
    public FooResult<FooData> getFooDataBad() {
        SomeData someData;
        try {
            someData = apiClient.getSomeData();
        } catch (SomeException e) {
            return new FooResult.Error<>(ErrorType.SOME);
        }
        
        AnotherData anotherData;
        try {
            anotherData = unreliableRepository.getAnotherData(someData);
        } catch (AnotherException e) {
            return new FooResult.Error<>(ErrorType.ANOTHER);
        }
        
        try {
            YetAnotherData yetAnotherData = unreliableRepository.getYetAnotherData(anotherData);
            return new FooResult.Success<>(new FooData(yetAnotherData.value()));
        } catch (YetAnotherException e) {
            return new FooResult.Error<>(ErrorType.YET_ANOTHER);
        }
    }
    
    /**
     * Good 버전: flatMap을 사용하여 예외를 정상 케이스로 변환
     * 
     * 장점:
     * 1. 예외를 FooResult로 변환하여 정상 케이스와 같이 처리
     * 2. 함수의 흐름이 명확함
     * 3. 에러 처리와 정상 흐름이 분리됨
     */
    public FooResult<FooData> getFooDataGood() {
        return getSomeData()
                .flatMap(this::toAnotherData)
                .flatMap(this::toYetAnotherData)
                .flatMap(yetAnotherData -> 
                    new FooResult.Success<>(new FooData(yetAnotherData.value()))
                );
    }
    
    private FooResult<SomeData> getSomeData() {
        try {
            return new FooResult.Success<>(apiClient.getSomeData());
        } catch (SomeException e) {
            return new FooResult.Error<>(ErrorType.SOME);
        }
    }
    
    private FooResult<AnotherData> toAnotherData(SomeData someData) {
        try {
            return new FooResult.Success<>(unreliableRepository.getAnotherData(someData));
        } catch (AnotherException e) {
            return new FooResult.Error<>(ErrorType.ANOTHER);
        }
    }
    
    private FooResult<YetAnotherData> toYetAnotherData(AnotherData anotherData) {
        try {
            return new FooResult.Success<>(unreliableRepository.getYetAnotherData(anotherData));
        } catch (YetAnotherException e) {
            return new FooResult.Error<>(ErrorType.YET_ANOTHER);
        }
    }
}

// 예제용 예외 클래스
class SomeException extends Exception {}
class AnotherException extends Exception {}
class YetAnotherException extends Exception {}

// 예제용 클래스들
class ApiClient {
    SomeData getSomeData() throws SomeException {
        // 예제를 위해 실제로는 예외가 발생할 수 있음
        // 여기서는 예외를 던지지 않지만, 실제 시나리오에서는 예외가 발생할 수 있음
        return new SomeData("some");
    }
}

class UnreliableRepository {
    AnotherData getAnotherData(SomeData someData) throws AnotherException {
        // 예제를 위해 실제로는 예외가 발생할 수 있음
        return new AnotherData("another");
    }
    
    YetAnotherData getYetAnotherData(AnotherData anotherData) throws YetAnotherException {
        // 예제를 위해 실제로는 예외가 발생할 수 있음
        return new YetAnotherData("yetAnother");
    }
}

