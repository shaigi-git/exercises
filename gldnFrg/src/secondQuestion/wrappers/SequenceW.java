package secondQuestion.wrappers;

import java.util.Iterator;

import secondQuestion.HtmlConvertible;
import secondQuestion.JavascriptConvertible;
import secondQuestion.Converter;
import secondQuestion.Utils;

/**
 * An abstract class that represents sequences data types
 * e.g.: Array, List
 *
 * @param <T> elements type
 */
public abstract class SequenceW<T> implements Iterable<T>, JavascriptConvertible, HtmlConvertible {

    private static final String JS_LIST_OPEN = "[ ";
    private static final String JS_LIST_CLOSE = " ]";
    private static final String HTML_ORDERED_LIST_OPEN = "<ol>";
    private static final String HTML_ORDERED_LIST_CLOSE = "</ol>";
    private static final String HTML_LIST_ITEM_OPEN = "<li>";
    private static final String HTML_LIST_ITEM_CLOSE = "</li>";

    @Override
    public abstract Iterator<T> iterator();

    @Override
    public String toJS() {
        String listItems = "";
        for (Iterator<T> iterator = iterator(); iterator.hasNext();) {
            T elem = (T) iterator.next();
            listItems += Converter.toJavaScript(elem) + ", ";
        }
        listItems = Utils.removeLastComma(listItems);
        return JS_LIST_OPEN + listItems + JS_LIST_CLOSE;
    }

    @Override
    public String toHTML() {
        String listItems = "";
        for (Iterator<T> iterator = iterator(); iterator.hasNext();) {
            T elem = (T) iterator.next();
            listItems += HTML_LIST_ITEM_OPEN + Converter.toHTML(elem) + HTML_LIST_ITEM_CLOSE;
        }
        return HTML_ORDERED_LIST_OPEN + listItems + HTML_ORDERED_LIST_CLOSE;
    }
}
