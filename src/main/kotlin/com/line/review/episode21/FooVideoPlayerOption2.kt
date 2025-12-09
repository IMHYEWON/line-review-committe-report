package com.line.review.episode21

/**
 * 옵션 2: play를 처음 호출 시 prepare를 실행
 * 
 * 장점:
 * 1. 인스턴스가 생성돼도 play가 호출되지 않을 가능성이 높고 prepare의 비용이 높은 경우에 효과적
 * 2. 지연 초기화로 불필요한 비용 절감
 * 
 * 단점:
 * 1. prepare에서 확정하는 속성을 가변(var)으로 해야 함
 */
class FooVideoPlayerOption2(
    private val videoUri: Uri
) {
    private var preparedValue: PreparedValue? = null

    fun play() {
        val preparedValue = prepare()

        // ... play `videoUri`.
        println("Playing video: ${videoUri.value} with ${preparedValue.data}")
    }

    private fun prepare(): PreparedValue {
        val existingValue = preparedValue
        if (existingValue != null) {
            return existingValue
        }

        // preparation logic
        val newValue = PreparedValue("prepared-${videoUri.value}")
        preparedValue = newValue
        return newValue
    }
}

/**
 * 옵션 2-2: lazy 사용
 * 
 * Kotlin의 lazy를 사용하여 최초 접근 시에 로직을 실행
 * prepare에서 확정하는 속성을 val로 만들 수 있음
 */
class FooVideoPlayerOption2Lazy(
    private val videoUri: Uri
) {
    private val preparedValue: PreparedValue by lazy {
        // preparation logic
        PreparedValue("prepared-${videoUri.value}")
    }

    fun play() {
        // ... play `videoUri` by using `preparedValue`
        println("Playing video: ${videoUri.value} with ${preparedValue.data}")
    }
}

