package com.line.review.episode21;

/**
 * 옵션 3: prepare 전에 play를 호출할 수 없도록 강제
 * 
 * 정적 타입을 사용하는 언어라면 prepare 전과 prepare 후의 타입을 나눠
 * prepare 후에만 play를 정의하는 방법
 * 잘못 사용하면 애초에 컴파일이 불가능하게 만듦
 * 
 * 장점:
 * 1. prepare의 실행 비용이 비싸서 호출자가 prepare의 실행 시점을 제어하고 싶을 때 유용
 * 2. prepare된 인스턴스를 FooVideoPlayer의 캐시로 보유할 수 있음
 * 3. 단계적인 초기화 상태를 관리할 수 있음
 * 4. 타입 안전성: 컴파일 타임에 잘못된 사용을 방지
 */
public class FooVideoPlayerOption3 {
    private final Uri videoUri;

    public FooVideoPlayerOption3(Uri videoUri) {
        this.videoUri = videoUri;
    }

    /**
     * prepare를 호출하여 PreparedFooVideoPlayer 인스턴스를 반환
     * 
     * @return PreparedFooVideoPlayer - play()를 호출할 수 있는 준비된 인스턴스
     */
    public PreparedFooVideoPlayer prepare() {
        // execute `prepare` logic
        PreparedValue preparedValue = new PreparedValue("prepared-" + videoUri.value());

        return new PreparedFooVideoPlayer(
                videoUri,
                preparedValue
        );
    }
}

/**
 * 준비된 비디오 플레이어
 * 
 * prepare()를 호출한 후에만 생성되는 인스턴스
 * play() 메서드를 호출할 수 있음
 */
class PreparedFooVideoPlayer {
    private final Uri videoUri;
    private final PreparedValue preparedValue;

    PreparedFooVideoPlayer(Uri videoUri, PreparedValue preparedValue) {
        this.videoUri = videoUri;
        this.preparedValue = preparedValue;
    }

    public void play() {
        // ... play `videoUri`.
        System.out.println("Playing video: " + videoUri.value() + " with " + preparedValue.data());
    }
}

