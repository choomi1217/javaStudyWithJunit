package String;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AsciiUnicodeTests {



    /**
     * 대소문자 매핑은 Character 클래스에서 지정한 유니코드 표준 버전을 기반으로 합니다.
     * */

    /**
     * String 은 보조 문자 가 서로게이트 쌍 으로 표현되는 UTF-16 형식의 문자열을 나타냅니다.
     * */

    /**
     * String 클래스는 유니코드 코드 단위(즉, char 값)를 처리하는 방법 외에
     * 유니코드 코드 포인트(즉, 문자)를 처리하는 방법을 제공합니다.
     * */


    /**
     *
     * why is Ascii and unicode created?
     *
     * 26 = [0]011010
     * A = ?
     * 컴퓨터는 0101101, 이진수만을 주고받고 해석할 수 있는데 문자는 숫자로 바꿀 수가 없었고
     * 문자를 숫자로 바꾸기 위해 만들어진 것이 Ascii , Unicode 입니다.
     *
     * Ascii ?
     *  : 0 ~ 127 까지 숫자로 영어를 맵핑해놓은 코드
     *   H  |  e  |  l  |  l  |  o
     *   72 | 101 | 108 | 108 | 111
     *   H = 72 = [0] 1001000
     *   각각의 숫자는 2진수로 7bit(1byte)입니다
     *
     *   이렇게 문자를 이진수로 바꾸는 과정을 인코딩이라고 합니다.
     *   디코딩을 위해선 역방향으로 해야합니다.
     *
     *   - Ascii의 인코딩
     *   즉, Ascii는 1byte당 한 문자
     *   H = 72 = 0100100 = 1byte ( 이를 단일 인코딩이라고 합니다 )
     *
     *   - 7bit? 8bit?
     *   어떤 사람은 7bit라고 하고 어떤 사람은 8bit라고 하는데
     *   확장 Ascii는 8bit라고 합니다.
     *   유니코드에 비핸 훨씬 부족하지만 일반 Ascii에 비해 가능한 문자가 2배 이상 늘었습니다.
     *   .
     *   .
     *   .
     *   그럼 Ascii로 모든걸 다 표현하게 된 것이 아닌가?
     *   -> 중국어와 아랍문자등 다른 독특한 문자들과 이모티콘들 때문에 유니코드가 만들어졌습니다.
     *
     *
     * Unicode ?
     *  : 영어와 기본 특수문자만 표현이 가능한 Ascii를 보완하고자
     *   문자의 억양, 이모티콘 및 기타 이상한 문자등 복잡한 것들을 처리하기 위해 만들어졌습니다.
     *   31bit 문자 집합입니다.
     *
     *   Ascii의 인코딩 방식은 아스키테이블에서 10진수의 숫자를 가져와 2진수로 변환하는 겁니다.
     *   그럼 Unicode의 인코딩 방식은?
     *
     *   - Unicode 인코딩
     *   :
     *     1. 코드 포인트를 코드화한 UCS-2와 UCS-4
     *     2. 변환 인코딩 형식 ( UCS Transformation Format = UTF | UTF-7, UTF-8, UTF-16, UTF-32 )
     *
     * */

    @Test
    void asciiTest() {

    }

    @Test
    void unicodeTest() {
        // "U+0041" == "\u0041"
        String s = "\\u0041";
    }

    @Test
    void stringByte() {
        byte oneByte = 1;
        byte[] by = new byte[]{oneByte};
        String str = new String(by);
        String str2 = "가b가12"; // 길이 : 10
        String word = new String(str2.getBytes(),0,9);
        System.out.println(word);
        System.out.println(new String(new byte[]{oneByte}));
        System.out.println(oneByte & 0xff);
        System.out.println(str);
        System.out.println(str.length());
    }

}
