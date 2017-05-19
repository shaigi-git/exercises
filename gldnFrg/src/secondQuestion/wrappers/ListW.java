package secondQuestion.wrappers;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * List wrapper
 *
 * @param <T> elements type
 */
public class ListW<T> extends SequenceW<T> {

    private ArrayList<T> l;

    public ListW(ArrayList<T> l) {
        if (l == null) throw new IllegalArgumentException("Cannot be null");
        this.l = l;
    }

    @Override
    public Iterator<T> iterator() {
        return l.listIterator();
    }
}
