package com.line.review.episode11

/**
 * 코드 품질 개선 기법 11편: 반복되는 호출에 함수도 지친다
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-11
 * 
 * Good 버전: 상태 확인을 함수 내부에서 수행
 * - 호출자가 상태를 확인할 필요 없음
 * - 상태 확인 로직이 수신 객체 내부에 캡슐화됨
 * - 겉으로 드러나는 상태 전이가 단순화됨
 */
class FriendStateUseCaseGood(
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
     * Good: 내부에서 상태를 확인하므로 호출자가 확인할 필요 없음
     * 이미 친구인 경우 아무것도 하지 않고 반환 (자연스러운 동작)
     */
    fun markAsFriend(userId: UserId) {
        // Good: 내부에서 상태를 확인
        if (isFriend(userId)) {
            return
        }
        
        // Actual logic to mark `userId` as a "friend".
        friendRepository.addFriend(currentUserId, userId)
    }
    
    /**
     * Good 버전: 함수 이름으로 조건을 명시하는 경우
     * 
     * '아무것도 하지 않는다'는 것을 특별히 강조해야 한다면
     * 함수 이름을 markAsFriendIfNotYet으로 바꾸거나 주석으로 조건을 설명
     */
    fun markAsFriendIfNotYet(userId: UserId) {
        // 이미 친구인 경우 아무것도 하지 않음
        if (isFriend(userId)) {
            return
        }
        
        // Actual logic to mark `userId` as a "friend".
        friendRepository.addFriend(currentUserId, userId)
    }
}

