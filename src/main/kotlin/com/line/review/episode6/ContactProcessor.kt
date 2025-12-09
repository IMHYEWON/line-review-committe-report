package com.line.review.episode6

/**
 * 코드 품질 개선 기법 6편
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-6
 * 
 */
class ContactProcessor {
    
    /**
     * ContactModel이 Person 타입이고 'friend' 상태라면
     * displayName을 조회해 normalizeEmoji를 실행한 결과를 반환
     */
    fun processContactBad(contact: ContactModel?): ReturnValue? {
        val friendName = (contact as?             // as? (안전캐스팅) : 공하면 캐스팅된 값, 실패하면 null
                ContactModel.Person)?.takeIf {    // ?.takeIf (안전 호출 연산자) : 내부조건이 true면 객체 반환, false면 null 반환
            it.isFriend
        }?.let { EmojiNormalizerKotlin.normalizeEmoji(it.displayName) } ?: return null 
        // let : 왼쪽이 null이 아니면 람다 실행하고 결과 반환, null이면 null
        // ?: (엘비스 연산자) : 앞의 값이 없으면 뒤의 값을 사용 
        
        return ReturnValue(friendName)
    }

    /**
     * Better 버전: '의미가 크게 구분되는' 곳에서 줄을 바꾸자
     * 
     * Bad 버전과 달리 연산자 앞에서 줄바꿈하여 가독성 개선
     * - (contact as? ContactModel.Person) 전체를 한 줄에
     * - ?.takeIf 앞에서 줄바꿈
     * - ?.let 앞에서 줄바선
     * - ?: 앞에서 줄바꿈
     */
    fun processContactBetter(contact: ContactModel?): ReturnValue? {
        val friendName = (contact as? ContactModel.Person)
            ?.takeIf { it.isFriend }
            ?.let { EmojiNormalizerKotlin.normalizeEmoji(it.displayName) }
            ?: return null
        
        // snip...
        // snip...
        
        return ReturnValue(friendName)
    }

    /**
     * Good 버전: 더 나은 리팩터링
     * 
     * - 필터 역할을 하는 부분과 변환 부분을 분리
     * - normalizeEmoji를 메서드 체인 외부로 이동
     */
    fun processContactGood(contact: ContactModel?): ReturnValue? {
        val friend = (contact as? ContactModel.Person)
            ?.takeIf { it.isFriend }
            ?: return null
        
        val friendName = EmojiNormalizerKotlin.normalizeEmoji(friend.displayName)
            ?: return null
        
        // snip...
        // snip...
        
        return ReturnValue(friendName)
    }
}

