package secondQuestion;

public interface JavascriptConvertible extends LangConvertible {

    /**
     * Every element that wants to be convertible to
     * JavaScript needs to implement this method
     * @return the JavaScript literal
     */
    String toJS();
}
