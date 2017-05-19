package secondQuestion.wrappers;

import java.util.HashMap;
import java.util.Map;

import secondQuestion.HtmlConvertible;
import secondQuestion.JavascriptConvertible;
import secondQuestion.Converter;
import secondQuestion.Utils;

/**
 * Map wrapper
 *
 * @param <T> elements type
 */
public class MapW implements JavascriptConvertible, HtmlConvertible {

    private static final String JS_MAP_OPEN = "{ ";
    private static final String JS_MAP_CLOSE = " }";
    private static final String HTML_DESCRIPTION_LIST_OPEN = "<dl>";
    private static final String HTML_DESCRIPTION_LIST_CLOSE = "</dl>";
    private static final String HTML_DESC_TERM_OPEN = "<dt>";
    private static final String HTML_DESC_TERM_CLOSE = "</dt>";
    private static final String HTML_DESC_TERM_DESC_OPEN = "<dd>";
    private static final String HTML_DESC_TERM_DESC_CLOSE = "</dd>";

    private HashMap<Object, Object> map;

    public MapW(HashMap<Object, Object> map) {
        if (map == null) throw new IllegalArgumentException("Cannot be null");
        this.map = map;
    }

    @Override
    public String toJS() {
        String pairs = "";
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            pairs += Converter.toJavaScript(key) + " : " + Converter.toJavaScript(val) + ", ";
        }
        pairs = Utils.removeLastComma(pairs);
        return JS_MAP_OPEN + pairs + JS_MAP_CLOSE;
    }

    @Override
    public String toHTML() {
        String pairs = "";
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            pairs += HTML_DESC_TERM_OPEN + Converter.toHTML(key) + HTML_DESC_TERM_CLOSE;
            pairs += HTML_DESC_TERM_DESC_OPEN + Converter.toHTML(val) + HTML_DESC_TERM_DESC_CLOSE;
        }
        return HTML_DESCRIPTION_LIST_OPEN + pairs + HTML_DESCRIPTION_LIST_CLOSE;
    }
}
