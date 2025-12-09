package com.line.review.episode21;

import java.util.function.Supplier;

/**
 * 옵션 2: play를 처음 호출 시 prepare를 실행
 * 
 * 장점:
 * 1. 인스턴스가 생성돼도 play가 호출되지 않을 가능성이 높고 prepare의 비용이 높은 경우에 효과적
 * 2. 지연 초기화로 불필요한 비용 절감
 * 
 * 단점:
 * 1. prepare에서 확정하는 속성을 가변으로 해야 함
 */
public class FooVideoPlayerOption2 {
    private final Uri videoUri;
    private PreparedValue preparedValue;

    public FooVideoPlayerOption2(Uri videoUri) {
        this.videoUri = videoUri;
    }

    public void play() {
        PreparedValue preparedValue = prepare();

        // ... play `videoUri`.
        System.out.println("Playing video: " + videoUri.value() + " with " + preparedValue.data());
    }

    private PreparedValue prepare() {
        PreparedValue existingValue = preparedValue;
        if (existingValue != null) {
            return existingValue;
        }

        // preparation logic
        PreparedValue newValue = new PreparedValue("prepared-" + videoUri.value());
        preparedValue = newValue;
        return newValue;
    }
}

/**
 * 옵션 2-2: Supplier를 사용한 지연 초기화
 * 
 * Java에서는 Supplier를 사용하여 최초 접근 시에 로직을 실행
 * prepare에서 확정하는 속성을 final로 만들 수 있음
 */
class FooVideoPlayerOption2Lazy {
    private final Uri videoUri;
    private final Supplier<PreparedValue> preparedValueSupplier;

    public FooVideoPlayerOption2Lazy(Uri videoUri) {
        this.videoUri = videoUri;
        this.preparedValueSupplier = () -> {
            // preparation logic
            return new PreparedValue("prepared-" + videoUri.value());
        };
    }

    public void play() {
        PreparedValue preparedValue = preparedValueSupplier.get();
        // ... play `videoUri` by using `preparedValue`
        System.out.println("Playing video: " + videoUri.value() + " with " + preparedValue.data());
    }
}

