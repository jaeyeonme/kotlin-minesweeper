# Kotlin Minesweeper

[NEXTSTEP 지뢰 찾기 미션](https://github.com/next-step/kotlin-minesweeper)을 Kotlin으로 구현한 콘솔 게임입니다. 보드 크기와 지뢰 개수를 입력해 게임을 시작하고, 좌표를 선택해 안전한 칸을 모두 열면 승리하고 지뢰를 열면 패배합니다.

## 주요 기능

- 입력한 높이와 너비로 보드를 만들고 지정한 개수만큼 지뢰를 무작위로 배치합니다.
- `가로, 세로` 형식의 좌표로 칸을 열고, 열린 안전 칸에는 인접한 지뢰 수를 표시합니다.
- 주변 지뢰가 없는 칸을 열면 연결된 영역과 인접한 안전 칸을 함께 엽니다.
- 지뢰를 열면 패배하고, 모든 안전 칸을 열면 승리합니다.

## 설계

### 지뢰 배치 방식 분리

실행에서는 지뢰 위치를 무작위로 정하고, 테스트에서는 `MinePlacementStrategy`를 통해 위치를 지정합니다. 같은 지뢰 배치를 재현할 수 있어 주변 지뢰 수와 칸 열기 결과를 반복해서 확인할 수 있습니다.

### 큐로 안전 영역 열기

주변 지뢰가 없는 칸을 열면 `PositionsToOpen` 큐에 확인할 위치를 추가하고 순차적으로 처리합니다. 주변 지뢰가 없는 연결된 칸과 그 주변의 안전한 칸을 열며, 이미 열린 칸과 지뢰는 처리하지 않습니다.

### 칸 열기와 게임 상태 판단 분리

`Board`는 칸을 여는 동작을 수행하고, `Game`은 보드 상태를 확인해 승리·패배를 판단하고 현재 `GameStatus`를 저장합니다.

`Board.open()`은 현재 보드 데이터에서 칸 열기 조건을 판단하며, `BoardSnapshot`은 보드 상태를 출력하거나 조회할 때 사용합니다.

## 테스트

전체 테스트와 코드 스타일은 다음 명령으로 확인합니다.

```shell
./gradlew clean test ktlintCheck
```

## 개발 기록

단계별 변경 내용은 Issue와 PR에 기록했습니다.

| 단계 | 내용 | 기록 |
| --- | --- | --- |
| Step 1 | 보드 생성과 지뢰 배치 | [Issue #1](https://github.com/jaeyeonme/kotlin-minesweeper/issues/1) · [PR #2](https://github.com/jaeyeonme/kotlin-minesweeper/pull/2) |
| Step 2 | 주변 지뢰 계산과 보드 출력 | [Issue #3](https://github.com/jaeyeonme/kotlin-minesweeper/issues/3) · [PR #4](https://github.com/jaeyeonme/kotlin-minesweeper/pull/4) |
| Step 3 | 칸 열기와 게임 진행 | [Issue #5](https://github.com/jaeyeonme/kotlin-minesweeper/issues/5) · [PR #6](https://github.com/jaeyeonme/kotlin-minesweeper/pull/6) |
| Step 4 | 게임 상태 판단 분리 | [Issue #7](https://github.com/jaeyeonme/kotlin-minesweeper/issues/7) · [PR #8](https://github.com/jaeyeonme/kotlin-minesweeper/pull/8) |

## 실행 방법

JDK 25 환경에서 실행합니다.

```shell
./gradlew run
```

높이, 너비, 지뢰 개수를 차례로 입력한 뒤 `가로, 세로` 형식으로 열 좌표를 입력합니다. 좌표는 `(1, 1)`부터 시작하며, 지뢰 위치는 게임을 시작할 때 무작위로 정해집니다.
