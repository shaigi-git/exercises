package secondQuestion.wrappers;

import secondQuestion.HtmlConvertible;
import secondQuestion.JavascriptConvertible;

public class PrimitiveW implements JavascriptConvertible, HtmlConvertible {

    private Object o;

    public PrimitiveW(Object o) {
        if (o == null) throw new IllegalArgumentException("Cannot be null");
        this.o = o;
    }

    @Override
    public String toJS() {
        return o.toString();
    }

    @Override
    public String toHTML() {
        return o.toString();
    }
}
