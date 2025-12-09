package com.line.review.episode6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ContactProcessorBad 테스트
 */
class ContactProcessorBadTest {
    
    private ContactProcessorBad processor;
    
    @BeforeEach
    void setUp() {
        processor = new ContactProcessorBad();
    }
    
    @Test
    void shouldReturnNormalizedName_WhenContactIsFriendPerson() {
        // Given
        ContactModel.Person friend = new ContactModel.Person("친구😊", true);
        
        // When
        ReturnValue result = processor.processContact(friend);
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo("친구"); // 이모지가 제거됨
    }
    
    @Test
    void shouldReturnNull_WhenContactIsNotFriend() {
        // Given
        ContactModel.Person notFriend = new ContactModel.Person("친구아님", false);
        
        // When
        ReturnValue result = processor.processContact(notFriend);
        
        // Then
        assertThat(result).isNull();
    }
    
    @Test
    void shouldReturnNull_WhenContactIsNull() {
        // When
        ReturnValue result = processor.processContact(null);
        
        // Then
        assertThat(result).isNull();
    }
    
    @Test
    void shouldReturnNormalizedName_WhenContactIsFriendPerson_Legacy() {
        // Given
        ContactModel.Person friend = new ContactModel.Person("친구😊", true);
        
        // When
        ReturnValue result = processor.processContactLegacy(friend);
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo("친구"); // 이모지가 제거됨
    }
}

