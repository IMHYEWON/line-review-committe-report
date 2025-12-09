package com.line.review.episode23;

import java.util.Map;

/**
 * 사용자 데이터를 조회하는 저장소
 */
public class UserRepository {
    private final Map<UserId, UserData> userDataMap = Map.of(
        new UserId("user1"), new UserData("Alice"),
        new UserId("user2"), new UserData("Bob"),
        new UserId("user3"), new UserData("Charlie")
    );
    
    public UserData getUserData(UserId userId) {
        UserData data = userDataMap.get(userId);
        if (data == null) {
            throw new IllegalArgumentException("User not found: " + userId.value());
        }
        return data;
    }
}

