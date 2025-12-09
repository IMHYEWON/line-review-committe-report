package com.line.review.episode6

/**
 * 이모지를 정규화하는 유틸리티 객체 (Kotlin 버전)
 * 
 * Java 버전의 EmojiNormalizer와 동일한 기능을 제공합니다.
 */
object EmojiNormalizerKotlin {
    
    /**
     * 이모지를 정규화합니다.
     * 실제 구현은 예제 목적이므로 간단한 변환만 수행합니다.
     * 
     * @param text 정규화할 텍스트
     * @return 정규화된 텍스트
     */
    fun normalizeEmoji(text: String?): String? {
        if (text == null) {
            return null
        }
        // 예제를 위한 간단한 정규화: 이모지 제거 또는 변환
        // 실제 구현에서는 더 복잡한 로직이 필요할 수 있습니다.
        return text.replace(Regex("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+"), "")
    }
}

