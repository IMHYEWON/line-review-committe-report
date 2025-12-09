package com.line.review.episode11

/**
 * 코드 품질 개선 기법 11편: 반복되는 호출에 함수도 지친다
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-11
 * 
 * 반환값으로 물어보기: 콜백 대신 반환값으로 결과를 반환
 * 
 * Bad: 콜백(onSucceeded)을 사용하는 경우
 * - 불필요한 의존성 순환 발생
 * - 동기식/비동기식 콜백인지 불명확
 * 
 * Good: 반환값(Boolean)으로 결과를 반환
 * - 의존성 순환 없음
 * - 동기식임이 명확함
 * - 호출자가 결과를 명확하게 알 수 있음
 */
class FriendStateUseCaseReturnValue(
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
     * Bad 버전: 콜백을 사용하는 경우
     * 
     * 문제점:
     * 1. onSucceeded 콜백으로 인해 불필요한 의존성 순환 발생
     * 2. 동기식 콜백인지 비동기식 콜백인지 불명확
     * 3. 호출자가 콜백을 제공해야 함
     */
    fun markAsFriendWithCallback(
        userId: UserId,
        onSucceeded: () -> Unit
    ) {
        if (isFriend(userId)) {
            return
        }
        
        // Actual logic to mark `userId` as a "friend".
        friendRepository.addFriend(currentUserId, userId)
        onSucceeded()
    }
    
    /**
     * Good 버전: 반환값으로 결과를 반환
     * 
     * 장점:
     * 1. 의존성 순환 없음
     * 2. 동기식임이 명확함
     * 3. 호출자가 결과를 명확하게 알 수 있음
     * 
     * @param userId 친구로 추가할 사용자 ID
     * @return true: 새로 친구로 추가됨, false: 이미 친구였음
     */
    fun markAsFriend(userId: UserId): Boolean {
        if (isFriend(userId)) {
            return false
        }
        
        // Actual logic to mark `userId` as a "friend".
        friendRepository.addFriend(currentUserId, userId)
        return true
    }
}

