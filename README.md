# LINE Review Committee 리포트 예제 프로젝트

이 프로젝트는 LY Corporation의 Review Committee에서 공유된 코드 품질 개선 기법을 공부하고 실행할 수 있도록 만든 Java 21 프로젝트입니다.

## 프로젝트 목적

Review Committee는 머지된 코드를 다시 한 번 리뷰하면서 리뷰어와 코드 작성자에게 피드백을 제공하는 활동입니다. 이 활동을 통해 얻은 지식과 인사이트를 Weekly Report로 공유하고 있으며, 이 프로젝트는 그 중 일반적으로 널리 적용할 수 있는 리포트의 예제를 학습하고 실행하는 것을 목적으로 합니다.

## 기술 스택

- **Java**: 21
- **빌드 도구**: Gradle 8.5
- **테스트 프레임워크**: JUnit 5
- **어설션 라이브러리**: AssertJ

## 프로젝트 구조

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/line/review/
│   │   │       └── App.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/line/review/
│               └── AppTest.java
├── build.gradle
├── settings.gradle
└── README.md
```

## 빌드 및 실행

### 프로젝트 빌드
```bash
./gradlew build
```

### 애플리케이션 실행
```bash
./gradlew run
```

### 테스트 실행
```bash
./gradlew test
```

## 참고 자료

- [DroidKaigi 2021 발표: 오래가는 코드 베이스의 '품질' 문제를 마주하다](https://droidkaigi.jp/2021/talks/123) (일본어)
- LINE Engineering Blog의 '코드 품질 개선 기법' 시리즈

## 라이선스

이 프로젝트는 학습 목적으로 작성되었습니다.

