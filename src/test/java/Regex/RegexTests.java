package Regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTests {

    @DisplayName("How to use")
    @Test
    void howToUseRegex() {

        String regEx = "foo";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regEx);

        // Now create matcher object.
        Matcher matcher = pattern.matcher("foo");

        assertThat(matcher.find()).isTrue();
    }

    @DisplayName("Matching word | What's matcher.find() ")
    @Test
    void regex1() throws IOException {
        Matcher matcher = Pattern.compile("foo").matcher("foo foo");
        int cnt = 0;
        while(matcher.find()){
            cnt++;
        }
        /**
         * boolean find() : 완벽하게 매칭되는 것이 있으면 TRUE 없으면 FALSE
         * */
        assertThat(cnt).isEqualTo(2);
    }

    @DisplayName("Matching word | What's matcher.matches() ")
    @Test
    void regex2() {
        Matcher matcher1 = Pattern.compile("foo").matcher("foo");
        Matcher matcher2 = Pattern.compile("foo").matcher("foo foo");
        Matcher matcher3 = Pattern.compile("\\d*").matcher("123");
        Matcher matcher4 = Pattern.compile("\\d").matcher("123");
        /**
         * boolean matches() : 완벽하게 일치하는 것이 있으면 TRUE 아니면 FALSE
         * */
        assertThat(matcher1.matches()).isTrue();
        assertThat(matcher2.matches()).isFalse();
        assertThat(matcher3.matches()).isTrue();
        assertThat(matcher4.matches()).isFalse();
    }

    @DisplayName(" . | 어떤 것이든 한 글자 매칭 ")
    @Test
    void regex3() {
        Matcher matcher1 = Pattern.compile(".").matcher("Hi");
        Matcher matcher2 = Pattern.compile("A.").matcher("Ayo Hi");
        int matchings = howManyMatched(matcher1);
        assertThat(matchings).isEqualTo(2);

        int matchings2 = howManyMatched(matcher2);
        assertThat(matchings).isEqualTo(1);
    }

    @DisplayName(" [] | 대괄호 안에 어떤 정규식중 하나라도 해당하면 매칭 ")
    @Test
    void regex4() {
        Matcher matcher1 = Pattern.compile("[abc]").matcher("a");
        assertThat(matcher1.matches()).isTrue();
    }

    @DisplayName(" [^] | 대괄호 안에 어떤 정규식중 하나라도 해당하면 미매칭 ")
    @Test
    void regex5() {
        Matcher matcher1 = Pattern.compile("[^abc]").matcher("a");
        assertThat(matcher1.matches()).isFalse();
    }

    @DisplayName(" * | 0개 혹은 그 이상 반복되는 문자 매칭 ")
    @Test
    void regex7() {
        Matcher matcher1 = Pattern.compile("a*").matcher("");
        Matcher matcher2 = Pattern.compile("a*").matcher("aaaaa");
        Matcher matcher3 = Pattern.compile("a*").matcher("ah");
        assertThat(matcher1.matches()).isTrue();
        assertThat(matcher2.matches()).isTrue();
        assertThat(matcher3.matches()).isFalse();
    }

    @DisplayName(" + | 1개 혹은 그 이상 반복되는 문자 매칭 ")
    @Test
    void regex8() {
        Matcher matcher1 = Pattern.compile("a+").matcher("");
        Matcher matcher2 = Pattern.compile("a+").matcher("a");
        Matcher matcher3 = Pattern.compile("a+").matcher("aaaaaa");
        assertThat(matcher1.matches()).isFalse();
        assertThat(matcher2.matches()).isTrue();
        assertThat(matcher3.matches()).isTrue();
    }

    @DisplayName(" ? | 앞에 붙은 문자 혹은 그룹이 있거나 없으면 매칭 ")
    @Test
    void regex9() {
        Matcher matcher1 = Pattern.compile("ab?").matcher("a");
        Matcher matcher2 = Pattern.compile("ab?").matcher("ab");
        assertThat(matcher1.matches()).isTrue();
        assertThat(matcher2.matches()).isTrue();
    }

    @DisplayName(" {0,3} | 0 ~ 3 개까지 반복되는 문자 매칭  ")
    @Test
    void regex10() {
        Matcher matcher1 = Pattern.compile("a{0,5}").matcher("");
        Matcher matcher2 = Pattern.compile("a{0,5}").matcher("aaaaa");
        Matcher matcher3 = Pattern.compile("[ab]{1,2}").matcher("a");
        Matcher matcher4 = Pattern.compile("[ab]{1,2}").matcher("aabb");
        Matcher matcher5 = Pattern.compile("(ab){1,3}").matcher("ababab");
        Matcher matcher6 = Pattern.compile("(ab){1,3}").matcher("abaabab");
        assertThat(matcher1.matches()).isTrue(); // a{0,5} > ""
        assertThat(matcher2.matches()).isTrue(); // a{0,5} > "aaaaa"
        assertThat(matcher3.matches()).isTrue(); // [ab]{1,2} > "a"
        assertThat(matcher4.matches()).isFalse(); // [ab]{1,2} > "aabb"
        assertThat(matcher5.matches()).isTrue(); // (ab){1,3} > "ababab"
        assertThat(matcher6.matches()).isFalse(); // (ab){1,3} > "abaabab"
    }

    @DisplayName(" (abc) | 소괄호 안의 문자열이 완벽히 맞아야 매칭 , 그룹이라고 부릅니다! ")
    @Test
    void regex11() {
        Matcher matcher1 = Pattern.compile("(abc)").matcher("abc");
        Matcher matcher2 = Pattern.compile("(abc)").matcher("ab");
        assertThat(matcher1.matches()).isTrue();
        assertThat(matcher2.matches()).isFalse();
    }

    @DisplayName(" a|b | 파이프의 앞에 것이거나 뒤의 것과 매칭 ")
    @Test
    void regex12() {
        Matcher matcher1 = Pattern.compile("aa|bb").matcher("aa");
        Matcher matcher2 = Pattern.compile("aa|bb").matcher("bb");
        Matcher matcher3 = Pattern.compile("aa|bb").matcher("aabb");
        assertThat(matcher1.matches()).isTrue();
        assertThat(matcher2.matches()).isTrue();
        assertThat(matcher3.matches()).isFalse();
    }

    @DisplayName(" \\ | 백슬래쉬는 이스케이프 문자입니다 ")
    @Test
    void regex13() {
        Matcher matcher1 = Pattern.compile("\'").matcher("'");
        assertThat(matcher1.matches()).isTrue();
    }

    @DisplayName(" ^ | 문장의 첫시작입니다 ")
    @Test
    void regex14() {
        Matcher matcher1 = Pattern.compile("^a").matcher("a");
        assertThat(matcher1.matches()).isTrue();
    }

    @DisplayName(" $ | 문장의 마지막입니다 ")
    @Test
    void regex15() {
        Matcher matcher1 = Pattern.compile("a$").matcher("a");
        assertThat(matcher1.matches()).isTrue();
    }

    private Matcher fileRun(String regEx) throws IOException {
        Path file = Path.of("src/test/resources").resolve("LoremInpsum.txt");
        String content = Files.readString(file);
        return Pattern.compile(regEx).matcher(content);
    }

    private int howManyMatched(Matcher matcher){
        int count = 0;
        while (matcher.find()){
            count ++;
        }
        return count;
    }

}
