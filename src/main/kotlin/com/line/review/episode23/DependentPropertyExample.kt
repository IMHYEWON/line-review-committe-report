package com.line.review.episode23

/**
 * 다른 속성에 의존하는 속성 예제
 * 
 * '어떤 속성이 특정 값일 때만 다른 속성이 의미를 갖는' 상황이 있습니다.
 * 예를 들어 textView라는 UI 요소가 있고 textView.isVisible이 표시 여부를 나타내며,
 * textView.text가 텍스트 내용을 나타낸다고 가정해 봅시다.
 * 이 경우 text가 의미를 갖는 것은 isVisible이 true일 때뿐입니다.
 */
class TextView {
    var isVisible: Boolean = false
    var text: String = ""
}

class DependentPropertyExample {
    
    /**
     * Bad 버전: 무의미한 대입을 배제하기 위한 조기 반환
     */
    fun updateTextViewBad(textView: TextView, someText: String) {
        if (someText.isEmpty()) {
            textView.isVisible = false
            return
        }
        textView.isVisible = true
        textView.text = someText
    }
    
    /**
     * Good 버전: true와 false 처리를 통합
     * 
     * isVisible이 false일 때는 text가 어떤 값이든 문제 없을 수도 있습니다.
     * 이 경우 다음과 같이 true와 false 처리를 통합할 수 있습니다.
     */
    fun updateTextViewGood(textView: TextView, someText: String) {
        textView.isVisible = someText.isNotEmpty()
        textView.text = someText
    }
}

