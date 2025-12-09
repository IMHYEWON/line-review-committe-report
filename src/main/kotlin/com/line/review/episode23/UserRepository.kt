package com.line.review.episode23

/**
 * 사용자 데이터를 조회하는 저장소
 */
class UserRepository {
    private val userDataMap = mapOf(
        UserId("user1") to UserData("Alice"),
        UserId("user2") to UserData("Bob"),
        UserId("user3") to UserData("Charlie")
    )
    
    fun getUserData(userId: UserId): UserData {
        return userDataMap[userId] ?: throw IllegalArgumentException("User not found: $userId")
    }
}

