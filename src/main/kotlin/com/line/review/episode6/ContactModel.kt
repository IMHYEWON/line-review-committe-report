package com.line.review.episode6

/**
 * 연락처 모델을 나타내는 sealed interface
 */
sealed interface ContactModel {
    
    /**
     * Person 타입의 연락처
     */
    data class Person(
        val displayName: String,
        val isFriend: Boolean
    ) : ContactModel
}

