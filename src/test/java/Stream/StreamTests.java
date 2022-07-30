package Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamTests {

    @DisplayName("스트림을 만드는 방법")
    @Test
    void howToMakeStream() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream<Integer> integerStream = list.stream(); // Collection으로부터 Stream 만들기
        Stream<String> stringStream = Stream.of("a","b","c"); //Stream의 배열 생성 | return Arrays.stream(values);
        Stream<Integer> evenStream = Stream.iterate(0, n->n+2); // 람다식
        Stream<Double> randomStream = Stream.generate(Math::random); // 람다식
        IntStream intStream = new Random().ints(5); // 난수 스트림
    }

    @DisplayName("스트림의 작동 원리")
    @Test
    void howStreamWorks() {
        Stream<String> stream = Stream.of("a","b","c");
        stream.distinct().limit(5).sorted().forEach(System.out::println);
    }

    @DisplayName("스트림의 중간연산자 Filter 맛보기")
    @Test
    void streamFilterSneakPeak() {
        String[] strings = {"C","B","A","D"};
        Stream<String> stream = Stream.of(strings);
        Stream<String> filterdStream = stream.filter(s -> s.equals("A"));
        filterdStream.forEach(System.out::println);
    }

    @DisplayName("스트림의 중간연산자 Distinct 맛보기")
    @Test
    void streamDistinctSneakPeak() {
        String[] strings = {"A","A","B","B"};
        Stream<String> stream = Stream.of(strings);
        Stream<String> distinctedStream = stream.distinct();
        distinctedStream.forEach(System.out::println);
    }

    @DisplayName("스트림의 중간연산자 sorted 맛보기")
    @Test
    void streamSortedSneakPeak() {
        String[] strings = {"C","B","A","D"};
        Stream<String> stream = Stream.of(strings);
        Stream<String> sortedStream = stream.sorted();
        sortedStream.forEach(System.out::println);
    }

    @DisplayName("스트림의 중간연산자 limited 맛보기")
    @Test
    void streamLimitedSneakPeak() {
        String[] strings = {"A","B","C","D","E","F","G","H","I","J","K"};
        Stream<String> stream = Stream.of(strings);
        Stream<String> limitedStream = stream.limit(4);
        limitedStream.forEach(System.out::println);
    }

    @DisplayName("스트림의 최종연산자 count 맛보기")
    @Test
    void streamCountSneakPeak() {
        String[] strings = {"A","B","C","D","E","F","G","H","I","J","K"};
        Stream<String> stream = Stream.of(strings);
        Stream<String> limitedStream = stream.limit(4);
        assertThat(limitedStream.count()).isEqualTo(4);
    }

    @DisplayName("스트림은 데이터 소스로부터 데이터를 읽기만 하고 변경하지 않습니다.")
    @Test
    void streamSpecialFeature1() {
        List<Integer> list = Arrays.asList(3,1,5,6,2,4);
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(sortedList);
    }

    @DisplayName("스트림은 Iterator처럼 일회용이다.")
    @Test
    void streamSpecialFeature2() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5);
        stream.forEach(s -> System.out.println(" ** "+s+" ** "));
        assertThatThrownBy(()->stream.count()).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("최종 연산 전까진 중간 연산이 수행되지 않는다. : 지연된 연산")
    @Test
    void streamSpecialFeature3() {
        // 1 ~ 45 범위의 난수를 발생시키는 무한 스트림
        IntStream randomeStream = new Random().ints(1,45);
        // 중간연산과 최종연산까지 마친 무한(?)스트림
        randomeStream.distinct().limit(6).sorted()
            .forEach(i->System.out.print( "," + i));
    }

    @DisplayName("스트림은 작업을 내부 반복으로 처리합니다.")
    @Test
    void streamSpecialFeature4() {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5);
        for (int i: integerList) {
            System.out.print(i + ",");
        }
        System.out.println("\n");
        integerList.stream().forEach(i -> System.out.print(i + "," ));
    }

    @DisplayName("스트림은 작업을 병렬로 처리 할 수 있습니다. 반대로 직렬도 됩니다.")
    @Test
    void streamSpecialFeature5() {
        Stream<String> stringStream = Stream.of("A","B","C");
        int parallelSum = stringStream.parallel().mapToInt(s->s.length()).sum();
        assertThat(parallelSum).isEqualTo(3);
        int sequentialSum = stringStream.sequential().mapToInt(s->s.length()).sum();
        assertThat(sequentialSum).isEqualTo(3);
    }

    @DisplayName("스트림은 기본형 스트림이 존재합니다.")
    @Test
    void streamSpecialFeature6() {
        
    }
}
