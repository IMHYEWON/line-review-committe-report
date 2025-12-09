package com.line.review.episode6;

/**
 * 연락처 모델을 나타내는 인터페이스
 */
public sealed interface ContactModel permits ContactModel.Person {
    
    /**
     * Person 타입의 연락처
     */
    final class Person implements ContactModel {
        private final String displayName;
        private final boolean isFriend;
        
        public Person(String displayName, boolean isFriend) {
            this.displayName = displayName;
            this.isFriend = isFriend;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public boolean isFriend() {
            return isFriend;
        }
    }
}

