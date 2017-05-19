package secondQuestion;

public interface HtmlConvertible extends LangConvertible {

    /**
     * Every element that wants to be convertible to
     * HTML needs to implement this method
     * @return the HTML literal
     */
    String toHTML();
}
