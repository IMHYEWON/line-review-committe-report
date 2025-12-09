package com.line.review.episode11

/**
 * FriendStateUseCaseGood를 호출하는 예제 코드
 * 
 * Good 버전: 상태 확인이 함수 내부에서 수행됨
 */
class FriendStateCallerGood(
    private val friendStateUseCase: FriendStateUseCaseGood
) {
    
    /**
     * Good 버전: 호출자가 상태를 확인할 필요 없음
     * 
     * 장점:
     * 1. 호출자가 isFriend()를 확인할 필요 없음
     * 2. 상태 확인 로직이 수신 객체 내부에 캡슐화됨
     * 3. 반복되는 호출 패턴이 제거됨
     * 4. 확인 누락으로 인한 버그 방지
     */
    fun addFriendIfNotYetGood(someUserId: UserId) {
        // Good: 상태 확인 없이 직접 호출 가능
        // 내부에서 이미 친구인지 확인하고 처리
        friendStateUseCase.markAsFriend(someUserId)
    }
    
    /**
     * Good 버전: 함수 이름으로 조건을 명시하는 경우
     */
    fun addFriendIfNotYetGoodWithExplicitName(someUserId: UserId) {
        // Good: 함수 이름으로 조건이 명시되어 있음
        friendStateUseCase.markAsFriendIfNotYet(someUserId)
    }
}

