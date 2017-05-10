package secondQuestion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import secondQuestion.wrappers.ArrayW;
import secondQuestion.wrappers.ListW;
import secondQuestion.wrappers.MapW;
import secondQuestion.wrappers.PrimitiveW;
import secondQuestion.wrappers.StringW;

public class Converter {

    private static final Set<Class<?>> PRIMITIVES =
            new HashSet<Class<?>>(Arrays.asList(
                    Boolean.class, Short.class, Integer.class, Long.class, Float.class, Double.class));

    /**
     * A helper function, its job is just to prevent code duplication for all the
     * types that are both JavaScriptConvertible and HtmlConvertible (i.e. instead
     * of adding all of these types both to wrapForJS and to wrapForHTML).
     * @param o object to wrap
     * @return an object that is both JavascriptConvertible and HtmlConvertible
     */
    private static LangConvertible wrapGeneric(Object o) {
        if (isTypePrimitive(o))         return new PrimitiveW(o);
        if (isType(o, Character.class)) return new StringW(o + "");
        if (isType(o, String.class))    return new StringW((String)o);
        if (o.getClass().isArray())     return handleArray(o);
        if (isType(o, HashMap.class)) {
            @SuppressWarnings("unchecked")
            // Type is manually checked
            HashMap<Object, Object> map = (HashMap<Object, Object>)o;
            return new MapW(map);
        }
        if (isType(o, ArrayList.class)) {
            @SuppressWarnings("unchecked")
            // Type is manually checked
            ArrayList<Object> arr = (ArrayList<Object>)o;
            return new ListW<Object>(arr);
        }

        throw new IllegalArgumentException(o.getClass() + " is not supported");
    }

    private static boolean isTypePrimitive(Object o) {
        return Converter.PRIMITIVES.contains(o.getClass());
    }

    /**
     * This function will wrap an object that is JavascriptConvertible.
     * If a type is only JavascriptConvertible (and not HtmlConvertible for
     * example) it should be added here.
     * @param o the object to wrap
     * @return a JavascriptConvertible object
     */
    private static JavascriptConvertible wrapForJS(Object o) {
        if (o == null) throw new IllegalArgumentException("Cannot be null");
        return (JavascriptConvertible) wrapGeneric(o);
    }

    /**
     * This function will wrap an object that is HtmlConvertible.
     * If a type is only HtmlConvertible (and not JavascriptConvertible for
     * example) it should be added here.
     * @param o the object to wrap
     * @return a HtmlConvertible object
     */
    private static HtmlConvertible wrapForHTML(Object o) {
        if (o == null) throw new IllegalArgumentException("Cannot be null");
        return (HtmlConvertible) wrapGeneric(o);
    }

    private static JavascriptConvertible handleArray(Object arr) {
        Object[] o1 = null;
        /*
         * ArrayW expects an Objects array.
         * If <arr> elements are primitives, they have to be manually boxed.
         * Else, <arr> can just be casted to an Objects array.
         */
        if (isPrimitivesArray(arr)) {

            o1 = new Object[Array.getLength(arr)];
            for (int i = 0; i < Array.getLength(arr); i++) {
                o1[i] = Array.get(arr, i);
            }
        } else {
            o1 = (Object[])arr;
        }
        return new ArrayW<Object>(o1);
    }

    private static boolean isType(Object o, Class<?> c) {
        return o.getClass().equals(c);
    }

    private static boolean isPrimitivesArray(Object arr) {
        return arr.getClass().getComponentType().isPrimitive();
    }

    /**
     * Question 2
     * ==========
     * Converts <o> to the corresponding JavaScript literal
     * @param o the object to convert
     * @return the JavaScript literal of <o>
     * @throws IllegalArgumentException in the case where <o> cannot be converted
     */
    public static String toJavaScript(Object o) throws IllegalArgumentException {
        return wrapForJS(o).toJS();
    }

    /**
     * Question 2
     * ==========
     * Converts <o> to the corresponding HTML literal
     * @param o the object to convert
     * @return the HTML literal of <o>
     * @throws IllegalArgumentException in the case where <o> cannot be converted
     */
    public static String toHTML(Object o) throws IllegalArgumentException {
        return wrapForHTML(o).toHTML();
    }
}
