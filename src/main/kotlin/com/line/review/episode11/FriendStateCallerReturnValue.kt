package com.line.review.episode11

/**
 * FriendStateUseCaseReturnValue를 호출하는 예제 코드
 * 
 * 반환값으로 물어보기: 콜백 대신 반환값 사용
 */
class FriendStateCallerReturnValue(
    private val friendStateUseCase: FriendStateUseCaseReturnValue
) {
    
    /**
     * Bad 버전: 콜백을 사용하는 경우
     * 
     * 문제점:
     * 1. 콜백으로 인해 의존성 순환 발생 가능
     * 2. 동기식/비동기식인지 불명확
     * 3. 호출 시점이 불명확
     */
    fun addFriendWithEventBad(someUserId: UserId) {
        // Bad: 콜백을 사용하여 의존성 순환 발생 가능
        friendStateUseCase.markAsFriendWithCallback(someUserId) {
            showEventPopup(Event.NEW_FRIEND) // 언제 호출되는지 불명확
        }
    }
    
    /**
     * Good 버전: 반환값으로 결과를 확인
     * 
     * 장점:
     * 1. 의존성 순환 없음
     * 2. 동기식임이 명확함
     * 3. 호출 시점이 명확함
     * 4. 실제로 친구가 추가되었는지 명확하게 알 수 있음
     */
    fun addFriendWithEventGood(someUserId: UserId) {
        // Good: 반환값으로 결과를 확인
        val isNewlyMarkedAsFriend = friendStateUseCase.markAsFriend(someUserId)
        if (isNewlyMarkedAsFriend) {
            showEventPopup(Event.NEW_FRIEND) // 실제로 친구가 추가되었을 때만 호출
        }
    }
    
    private fun showEventPopup(event: Event) {
        // 이벤트 팝업 표시 로직
        println("Event popup: $event")
    }
}

