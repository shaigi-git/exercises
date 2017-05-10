package secondQuestion.wrappers;

import secondQuestion.HtmlConvertible;
import secondQuestion.JavascriptConvertible;
import secondQuestion.Utils;

/**
 * String wrapper
 */
public class StringW implements JavascriptConvertible, HtmlConvertible {

    private String str;

    public StringW(String str) {
        if (str == null) throw new IllegalArgumentException("Cannot be null");
        this.str = str;
    }

    @Override
    public String toJS() {
        return Utils.wrapWithQuotes(this.str);
    }

    @Override
    public String toHTML() {
        return this.str;
    }

}
