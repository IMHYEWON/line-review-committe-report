package com.line.review.episode23

/**
 * 연속 함수 호출 중 예외 예제
 * 
 * 함수 중 여러 곳에서 예외가 발생하는 경우
 * 조기 반환을 적용하려고 하면 코드가 복잡해질 수 있습니다.
 * 이런 경우에는 Success에 대해서만 작동하는 함수인 flatMap을 이용하면
 * 더 명확하게 만들 수 있습니다.
 */
sealed class FooResult<out T> {
    data class Success<T>(val value: T) : FooResult<T>()
    data class Error(val errorType: ErrorType) : FooResult<Nothing>()
    
    fun <U> flatMap(action: (T) -> FooResult<U>): FooResult<U> = when (this) {
        is Success -> action(value)
        is Error -> this
    }
}

enum class ErrorType {
    SOME, ANOTHER, YET_ANOTHER
}

class SomeData(val value: String)
class AnotherData(val value: String)
class YetAnotherData(val value: String)
class FooData(val value: String)

class SequentialExceptionExample {
    
    private val apiClient = ApiClient()
    private val unreliableRepository = UnreliableRepository()
    
    /**
     * Bad 버전: 여러 곳에서 조기 반환으로 예외 처리
     * 
     * 문제점:
     * 1. 각 단계마다 try-catch와 조기 반환 반복
     * 2. 코드가 복잡하고 읽기 어려움
     * 3. 에러 처리와 정상 흐름이 섞여 있음
     */
    fun getFooDataBad(): FooResult<FooData> {
        val someData = try {
            apiClient.getSomeData()
        } catch (_: SomeException) {
            return FooResult.Error(ErrorType.SOME)
        }
        
        val anotherData = try {
            unreliableRepository.getAnotherData(someData)
        } catch (_: AnotherException) {
            return FooResult.Error(ErrorType.ANOTHER)
        }
        
        return try {
            val yetAnotherData = unreliableRepository.getYetAnotherData(anotherData)
            FooResult.Success(FooData(yetAnotherData.value))
        } catch (_: YetAnotherException) {
            FooResult.Error(ErrorType.YET_ANOTHER)
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
    fun getFooDataGood(): FooResult<FooData> =
        getSomeData()
            .flatMap(::toAnotherData)
            .flatMap(::toYetAnotherData)
            .flatMap { yetAnotherData ->
                FooResult.Success(FooData(yetAnotherData.value))
            }
    
    private fun getSomeData(): FooResult<SomeData> = try {
        FooResult.Success(apiClient.getSomeData())
    } catch (_: SomeException) {
        FooResult.Error(ErrorType.SOME)
    }
    
    private fun toAnotherData(someData: SomeData): FooResult<AnotherData> = try {
        FooResult.Success(unreliableRepository.getAnotherData(someData))
    } catch (_: AnotherException) {
        FooResult.Error(ErrorType.ANOTHER)
    }
    
    private fun toYetAnotherData(anotherData: AnotherData): FooResult<YetAnotherData> = try {
        FooResult.Success(unreliableRepository.getYetAnotherData(anotherData))
    } catch (_: YetAnotherException) {
        FooResult.Error(ErrorType.YET_ANOTHER)
    }
}

// 예제용 예외 클래스
class SomeException : Exception()
class AnotherException : Exception()
class YetAnotherException : Exception()

// 예제용 클래스들
class ApiClient {
    fun getSomeData(): SomeData = SomeData("some")
}

class UnreliableRepository {
    fun getAnotherData(someData: SomeData): AnotherData = AnotherData("another")
    fun getYetAnotherData(anotherData: AnotherData): YetAnotherData = YetAnotherData("yetAnother")
}

