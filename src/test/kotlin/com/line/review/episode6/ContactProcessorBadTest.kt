package com.line.review.episode6

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

/**
 * ContactProcessorBad 테스트 (Kotlin 버전)
 */
class ContactProcessorBadTest {
    
    private lateinit var processor: ContactProcessor
    
    @BeforeEach
    fun setUp() {
        processor = ContactProcessor()
    }
    
    @Test
    fun shouldReturnNormalizedName_WhenContactIsFriendPerson() {
        // Given
        val friend = ContactModel.Person("친구😊", true)
        
        // When
        val result = processor.processContactBad(friend)
        
        // Then
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo("친구") // 이모지가 제거됨
    }
    
    @Test
    fun shouldReturnNull_WhenContactIsNotFriend() {
        // Given
        val notFriend = ContactModel.Person("친구아님", false)
        
        // When
        val result = processor.processContactBad(notFriend)
        
        // Then
        assertThat(result).isNull()
    }
    
    @Test
    fun shouldReturnNull_WhenContactIsNull() {
        // When
        val result = processor.processContactBad(null)
        
        // Then
        assertThat(result).isNull()
    }
}

