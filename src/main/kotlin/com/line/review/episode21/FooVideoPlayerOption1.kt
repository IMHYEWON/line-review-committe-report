package com.line.review.episode21

/**
 * 옵션 1: 초기화 시 prepare를 실행
 * 
 * 장점:
 * 1. 많은 속성을 읽기 전용(val)으로 만들 수 있음
 * 2. prepare 시점에 처음으로 값이 결정되는 속성에도 val을 사용 가능
 * 
 * 단점:
 * 1. 이니셜라이저 내에서 호출한 함수가 초기화되지 않은 속성을 읽어 오는 버그 발생 가능
 * 2. 생성자에는 다양한 제약이 있음 (suspend 불가 등)
 * 3. 복잡한 로직이나 부작용이 큰 로직을 작성하면 문제가 될 수 있음
 */
class FooVideoPlayerOption1(
    private val videoUri: Uri
) {
    private val preparedValue: PreparedValue

    init {
        // execute `prepare` logic
        preparedValue = PreparedValue("prepared-${videoUri.value}")
    }

    fun play() {
        // ... play `videoUri`.
        println("Playing video: ${videoUri.value} with ${preparedValue.data}")
    }
}

/**
 * 옵션 1-2: 팩토리 함수 사용
 * 
 * 생성자를 private으로 설정하고 별도의 팩토리 함수를 정의
 * 팩토리 함수 내에 prepare에 해당하는 로직을 작성
 */
class FooVideoPlayerOption1Factory private constructor(
    private val videoUri: Uri,
    private val preparedValue: PreparedValue
) {
    fun play() {
        // ... play `videoUri`.
        println("Playing video: ${videoUri.value} with ${preparedValue.data}")
    }

    companion object {
        /**
         * 팩토리 함수: prepare 로직을 실행한 후 인스턴스 생성
         */
        fun createInstance(videoUri: Uri): FooVideoPlayerOption1Factory {
            // execute `prepare` logic
            val preparedValue = PreparedValue("prepared-${videoUri.value}")

            return FooVideoPlayerOption1Factory(
                videoUri,
                preparedValue
            )
        }
    }
}

