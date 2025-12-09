package com.line.review.episode6;

/**
 * 코드 품질 개선 기법 6편
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-6
 */
public class ContactProcessor {
    
    /**
     * Bad 버전: 부적절한 줄바꿈으로 인해 가독성이 떨어짐
     * 
     * 원문 Kotlin: val friendName = (contact as?
     *              ContactModel.Person)?.takeIf {
     *          it.isFriend
     *      }?.let { normalizeEmoji(it.displayName) } ?: return null
     * 
     * Java로 번역: 원문의 부적절한 줄바꿈 패턴을 그대로 반영
     * - instanceof 뒤에서 줄바꿈 (원문의 as? 뒤 줄바꿈과 유사)
     * - 괄호 닫힘 뒤에서 줄바꿈 (원문의 Person) 뒤 줄바꿈과 유사)
     * - 조건문 중간에서 줄바꿈 (원문의 takeIf { 뒤 줄바꿈과 유사)
     * 
     * @param contact 연락처 모델
     * @return 처리된 친구 이름이 포함된 ReturnValue, 조건에 맞지 않으면 null
     */
    public ReturnValue processContactBad(ContactModel contact) {
        // Bad: 부적절한 줄바꿈으로 인해 가독성이 떨어짐
        String friendName = (contact instanceof ContactModel.Person person) 
                ? (person.isFriend() 
                        ? EmojiNormalizer.normalizeEmoji(
                                person.getDisplayName()) 
                        : null)
                : null;
        
        if (friendName == null) {
            return null;
        }
        
        // snip...
        // snip...
        
        return new ReturnValue(friendName);
    }
    
    /**
     * Better 버전: '의미가 크게 구분되는' 곳에서 줄을 바꾸자
     * 
     * Bad 버전과 달리 연산자 앞에서 줄바꿈하여 가독성을 개선했습니다.
     * - (contact instanceof ContactModel.Person person) 전체를 한 줄에
     * - 삼항 연산자의 ? 앞에서 줄바꿈
     * - 메서드 호출 인자에서 줄바꿈
     * 
     * @param contact 연락처 모델
     * @return 처리된 친구 이름이 포함된 ReturnValue, 조건에 맞지 않으면 null
     */
    public ReturnValue processContactBetter(ContactModel contact) {
        // Better: 의미가 크게 구분되는 곳에서 줄바꿈
        String friendName = (contact instanceof ContactModel.Person person)
                ? (person.isFriend()
                        ? EmojiNormalizer.normalizeEmoji(person.getDisplayName())
                        : null)
                : null;
        
        if (friendName == null) {
            return null;
        }
        
        // snip...
        // snip...
        
        return new ReturnValue(friendName);
    }
    
    /**
     * Good 버전: 더 나은 리팩터링
     * 
     * 원문에서 제안하는 더 나은 방법:
     * - 필터 역할을 하는 부분과 변환 부분을 분리
     * - normalizeEmoji를 메서드 체인 외부로 이동
     * 
     * @param contact 연락처 모델
     * @return 처리된 친구 이름이 포함된 ReturnValue, 조건에 맞지 않으면 null
     */
    public ReturnValue processContactGood(ContactModel contact) {
        // Good: 필터 부분과 변환 부분을 분리
        ContactModel.Person friend = (contact instanceof ContactModel.Person person)
                ? (person.isFriend() ? person : null)
                : null;
        
        if (friend == null) {
            return null;
        }
        
        String friendName = EmojiNormalizer.normalizeEmoji(friend.getDisplayName());
        
        if (friendName == null) {
            return null;
        }
        
        // snip...
        // snip...
        
        return new ReturnValue(friendName);
    }
}

