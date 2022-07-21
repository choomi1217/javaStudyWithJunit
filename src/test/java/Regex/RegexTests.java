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
         * boolean find() : 매칭되는 것이 있으면 TRUE 없으면 FALSE
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
