package com.line.review.episode23

/**
 * 코드 품질 개선 기법 23편: 반환의 끝이 에지 케이스의 끝
 * 
 * 원문: https://techblog.lycorp.co.jp/ko/techniques-for-improving-code-quality-23
 * 
 * Bad 버전: 불필요한 조기 반환
 * 
 * 조기 반환은 무조건 적용한다고 좋은 것은 아닙니다.
 * 조기 반환 적용 여부는 에러 케이스와 정상 케이스에서의 처리가
 * 어느 정도 다른지에 따라 달라져야 합니다.
 * 만약 에러 케이스와 정상 케이스의 처리가 동일하다면
 * 해당 '에러'는 에러 케이스로 처리하지 않고 정상 케이스로 처리하는 것이
 * 코드를 단순화하기 쉽습니다.
 */
class EarlyReturnExample(
    private val repository: UserRepository = UserRepository()
) {
    
    /**
     * Bad 버전: 불필요한 조기 반환
     * 
     * 문제점:
     * 1. userIds.isEmpty()와 userIds.size == 1을 에러 케이스로 처리
     * 2. 실제로는 정상 케이스와 동일한 처리를 수행
     * 3. 코드가 복잡하고 중복됨
     */
    fun getUserNamesBad(userIds: List<UserId>): List<String> {
        if (userIds.isEmpty()) {
            return emptyList()
        }
        if (userIds.size == 1) {
            val userData = repository.getUserData(userIds[0])
            return listOf(userData.name)
        }
        
        return userIds.asSequence()
            .map(repository::getUserData)
            .map(UserData::name)
            .toList()
    }
    
    /**
     * Good 버전: 정상 케이스로 통합
     * 
     * 장점:
     * 1. 빈 리스트와 단일 요소도 정상 케이스로 처리
     * 2. 코드가 단순하고 명확함
     * 3. 중복 제거
     */
    fun getUserNamesGood(userIds: List<UserId>): List<String> =
        userIds.asSequence()
            .map(repository::getUserData)
            .map(UserData::name)
            .toList()
}

