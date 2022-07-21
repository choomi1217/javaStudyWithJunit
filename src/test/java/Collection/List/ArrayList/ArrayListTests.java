package Collection.List.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTests {
    private ArrayList<Integer> arrayList;

    private void setUpAndAdd() {
        arrayList = new ArrayList<Integer>();
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
    }

    @DisplayName(" 1-1. ArrayList-Constructor(int) ")
    @Test
    void arrayListConstructor() {
        assertThatThrownBy(() -> new ArrayList<>(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName(" 1-2. ArrayList-Constructor(Collection) ")
    @Test
    void arrayListConstructor2() {
        assertThatThrownBy(() -> new ArrayList<>(-1))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName(" 2. ArrayList.add(E e) ")
    @Test
    void arrayListAddTest() {
        arrayList.add(100);
        assertThat(arrayList.contains(100)).isTrue();
    }

    @DisplayName(" 3. ArrayList.sort(Collection.List<T> list) ")
    @Test
    void arrayListSortTest(int ints) {
        ArrayList<Integer> sortedArrList = new ArrayList<Integer>(Arrays.asList(1,2,3));
        Collections.sort(arrayList);
         assertThat(arrayList.equals(sortedArrList)).isTrue();
    }

}
