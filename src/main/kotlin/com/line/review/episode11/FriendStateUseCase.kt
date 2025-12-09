package com.line.review.episode11

/**
 * 코드 품질 개선 기법 11편: 반복되는 호출에 함수도 지친다
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-11
 * 
 * Bad 버전: 호출자가 상태를 확인하고 변경하는 패턴
 * - 호출자 측에서 isFriend()를 확인한 후 markAsFriend()를 호출
 * - 상태 확인 로직이 호출자에게 노출됨
 * - 반복되는 호출 패턴이 발생
 */
class FriendStateUseCase(
    private val currentUserId: UserId,
    private val friendRepository: FriendRepository = FriendRepository()
) {
    
    /**
     * 특정 사용자가 친구인지 확인
     */
    fun isFriend(userId: UserId): Boolean {
        return friendRepository.isFriend(currentUserId, userId)
    }
    
    /**
     * 특정 사용자를 친구로 추가
     * 
     * Bad: 호출자가 isFriend()를 먼저 확인해야 함
     * 이미 친구인 경우 예외가 발생할 수 있음
     */
    fun markAsFriend(userId: UserId) {
        // Bad: 호출자가 이미 !isFriend()를 확인했다고 가정
        // 만약 이미 친구인 상태에서 호출하면 예외가 발생할 수 있음
        if (friendRepository.isFriend(currentUserId, userId)) {
            throw IllegalStateException("User is already a friend")
        }
        
        friendRepository.addFriend(currentUserId, userId)
    }
}

/**
 * 친구 정보를 저장하는 저장소 (예제용)
 */
class FriendRepository {
    private val friendships = mutableSetOf<Pair<UserId, UserId>>()
    
    fun isFriend(userId1: UserId, userId2: UserId): Boolean {
        return friendships.contains(userId1 to userId2) || 
               friendships.contains(userId2 to userId1)
    }
    
    fun addFriend(userId1: UserId, userId2: UserId) {
        friendships.add(userId1 to userId2)
    }
}

