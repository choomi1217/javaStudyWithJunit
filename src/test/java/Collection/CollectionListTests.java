package Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CollectionListTests {

    // Collection <- Collection.List <- ArrayList, LinkedList, stack, vector ...

    private Vector<String> vector;
    private ArrayList<String> arrayList;
    private LinkedList<String> linkedList;
    private Stack<String> stack;

    @BeforeEach
    void setUpAndAdd() {
        vector = new Vector<String>();
        vector.add("Hello");
        vector.add("World!");
        vector.add("영미");

        arrayList = new ArrayList<String>();
        arrayList.add("Hello");
        arrayList.add("World!");
        arrayList.add("영미");

        linkedList = new LinkedList<String>();
        linkedList.add("Hello");
        linkedList.add("World!");
        linkedList.add("영미");

        stack = new Stack<String>();
        stack.add("Hello");
        stack.add("World!");
        stack.add("영미");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3"})
    void addAll(String strings) {

        List<String> stringList = Arrays.asList(strings.split(","));

        vector.addAll(stringList);
        arrayList.addAll(stringList);
        linkedList.addAll(stringList);
        stack.addAll(stringList);

        Assertions.assertAll(
                () -> assertThat(vector.containsAll(stringList)).isTrue(),
                () -> assertThat(arrayList.containsAll(stringList)).isTrue(),
                () -> assertThat(linkedList.containsAll(stringList)).isTrue(),
                () -> assertThat(stack.containsAll(stringList)).isTrue()
        );
    }
}
