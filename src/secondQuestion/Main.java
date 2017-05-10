package secondQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    // See Test2.java for more cases
    
    public static void main(String[] args) {
        Converter.toJavaScript(3);
        Converter.toHTML(3);
        Converter.toJavaScript(false);
        Converter.toHTML(false);
        Converter.toJavaScript("yo");
        Converter.toHTML("yo");
        List<Double> l = new ArrayList<>(Arrays.asList(1.1, 2.5));
        Converter.toJavaScript(l);
        Converter.toHTML(l);
        int arr1[] = {1,2,3,4};
        Converter.toJavaScript(arr1);
        Converter.toHTML(arr1);
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("zero", 0);
        map1.put("one", 1);
        map1.put("two", 2);
        Converter.toJavaScript(map1);
        Converter.toHTML(map1);
        Object arr2[] = { 1, 2, map1, "string"};
        Converter.toJavaScript(arr2);
        Converter.toHTML(arr2);
    }
}
