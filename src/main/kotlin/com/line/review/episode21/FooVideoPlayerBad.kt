package com.line.review.episode21

/**
 * 코드 품질 개선 기법 21편: 생성자를 두드려 보고 건너라
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-21
 * 
 * Bad 버전: 준비되지 않은 상태를 안전하게 처리하지 못함
 * 
 * 문제점:
 * 1. prepare()를 호출하지 않고 play()를 호출하면 예외 발생
 * 2. 인스턴스가 전달될 때 prepare가 호출됐는지 여부를 알 수 없음
 * 3. 잘못 호출하면 예외가 발생하여 버그의 원인이 됨
 * 4. 주의해서 사용해야 하는 클래스는 버그의 원인이 될 수 있음
 */
class FooVideoPlayerBad(
    private val videoUri: Uri
) {
    private var preparedValue: PreparedValue? = null

    /**
     * 동영상 재생을 위한 준비 작업
     * 
     * play()를 호출하기 전에 반드시 호출해야 함
     */
    fun prepare() {
        if (preparedValue != null) {
            error("Already prepared")
        }

        // execute `prepare` logic
        preparedValue = PreparedValue("prepared-${videoUri.value}")
    }

    /**
     * 동영상 재생
     * 
     * prepare()를 호출하지 않고 호출하면 IllegalStateException 발생
     */
    fun play() {
        val currentValue = preparedValue
        if (currentValue == null) {
            error("Not prepared yet")
        }

        // ... play `videoUri`.
        println("Playing video: ${videoUri.value} with ${currentValue.data}")
    }
}

