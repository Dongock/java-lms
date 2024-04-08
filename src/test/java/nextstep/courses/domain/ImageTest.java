package nextstep.courses.domain;

import nextstep.sessions.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ImageTest {

    @Test
    @DisplayName("강의 커버 이미지의 크기가 1MB보다 크면 오류가 발생")
    void imageByteTest() {
        assertThatThrownBy(
                () -> new Image(1001, "gif", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 크기는 1MB");
    }

    @Test
    @DisplayName("강의 커버 이미지의 타입이 gif, jpg, jpeg, png, svg가 아니라면 오류가 발생")
    void imageTypeTest() {
        assertThatThrownBy(
                () -> new Image(1000, "giff", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이미지 타입입니다.");
    }
}
