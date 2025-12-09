package com.line.review.episode11

/**
 * FriendStateUseCase를 호출하는 예제 코드
 * 
 * Bad 버전: 호출자가 상태를 확인하고 변경하는 패턴
 */
class FriendStateCaller(
    private val friendStateUseCase: FriendStateUseCase
) {
    
    /**
     * Bad 버전: 호출자가 상태를 확인하고 변경
     * 
     * 문제점:
     * 1. 호출자가 isFriend()를 먼저 확인해야 함
     * 2. 상태 확인 로직이 호출자에게 노출됨
     * 3. 반복되는 호출 패턴 (receiver.a() 후 receiver.b())
     * 4. 확인이 누락되면 버그 발생 가능
     */
    fun addFriendIfNotYetBad(someUserId: UserId) {
        // Bad: 호출자가 상태를 확인하고 변경
        if (!friendStateUseCase.isFriend(someUserId)) {
            friendStateUseCase.markAsFriend(someUserId)
        }
    }
    
    /**
     * Bad 버전: 이벤트 팝업을 보여주는 경우
     * 
     * 문제점:
     * 1. 상태 확인과 변경이 분리되어 있음
     * 2. 성공 여부를 알기 어려움
     */
    fun addFriendWithEventBad(someUserId: UserId) {
        // Bad: 상태 확인 후 변경, 성공 여부를 알기 어려움
        if (!friendStateUseCase.isFriend(someUserId)) {
            friendStateUseCase.markAsFriend(someUserId)
            showEventPopup(Event.NEW_FRIEND) // 실제로 친구가 추가되었는지 불명확
        }
    }
    
    private fun showEventPopup(event: Event) {
        // 이벤트 팝업 표시 로직
        println("Event popup: $event")
    }
}

