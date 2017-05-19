package secondQuestion.wrappers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Array wrapper
 *
 * @param <T> elements type
 */
public class ArrayW<T> extends SequenceW<T> {

    private T[] arr;

    public ArrayW(T[] arr) {
        if (arr == null) throw new IllegalArgumentException("Cannot be null");
        this.arr = arr;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> l = Arrays.asList(arr);
        return l.listIterator();
    }
}
