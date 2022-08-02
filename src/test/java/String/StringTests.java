package String;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTests {
    /**
     * String class의 Java document랑 각종 영상들 보며 테스트한 코드입니다.
     * */

    @Test
    void makeString() {
        String test1 = "abc";
        char[] data = {'a', 'b', 'c'};
        String test2 = new String(data);
        assertThat(test1).isEqualTo(test2);
    }


    @DisplayName("이름은 모두 Jonny입니다. 그녀와 그는 같습니다. 하지만 무언가와 그녀는 다릅니다.")
    @Test
    void isThatSameObject() {
        String herName = "Jonny";
        String hisName = "Jonny";
        String anotherName = new String("Jonny");

        assertThat(herName==hisName).isTrue();
        assertThat(herName==anotherName).isFalse();

    }
}
