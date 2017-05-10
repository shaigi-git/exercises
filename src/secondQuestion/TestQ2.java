package secondQuestion;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestQ2 {

    HashMap<String, Integer> map1;
    List<String> list1;

    public TestQ2() {
        map1 = new HashMap<String, Integer>();
        map1.put("zero", 0);
        map1.put("one", 1);
        map1.put("two", 2);

        list1 = new ArrayList<String>(Arrays.asList("ABC", "DE", "FGHIJ"));
    }

    private void execute(Object o, String js, String html) {
        assertEquals(js, Converter.toJavaScript(o));
        assertEquals(html, Converter.toHTML(o));
    }

    @Test
    public void testToJsObjectPrimitivesStrings() {
        execute(3, "3", "3");
        execute(3.8, "3.8", "3.8");
        execute(false, "false", "false");
        execute("yo", "\"yo\"", "yo");
        execute('v', "\"v\"", "v");
    }

    @Test
    public void testToJsObjectMap1() {
        execute(map1, "{ \"zero\" : 0, \"one\" : 1, \"two\" : 2 }",
                "<dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl>");
    }

    @Test
    public void testToJsObjectMap2() {
        HashMap<String, HashMap<String, Integer>> map2 = new HashMap<String, HashMap<String, Integer>>();
        map2.put("aaa", map1);
        map2.put("bbb", map1);

        execute(map2, "{ \"aaa\" : { \"zero\" : 0, \"one\" : 1, \"two\" : 2 }, \"bbb\" : { \"zero\" : 0, \"one\" : 1, \"two\" : 2 } }",
                "<dl><dt>aaa</dt><dd><dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl></dd><dt>bbb</dt><dd><dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl></dd></dl>");
    }

    @Test
    public void testToJsObjectMap3() {
        HashMap<String, HashMap<String, Integer>> map2 = new HashMap<String, HashMap<String, Integer>>();
        map2.put("aaa", map1);
        map2.put("bbb", map1);
        // override existing key's value:
        map2.put("aaa", new HashMap<String, Integer>());
        
        execute(map2, "{ \"aaa\" : {  }, \"bbb\" : { \"zero\" : 0, \"one\" : 1, \"two\" : 2 } }",
                "<dl><dt>aaa</dt><dd><dl></dl></dd><dt>bbb</dt><dd><dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl></dd></dl>");
    }
    
    @Test
    public void testToJsObjectList1() {
        List<Double> l = new ArrayList<>(Arrays.asList(1.1, 2.5));
        execute(l, "[ 1.1, 2.5 ]", "<ol><li>1.1</li><li>2.5</li></ol>");
    }

    @Test
    public void testToJsObjectList2() {
        execute(list1, "[ \"ABC\", \"DE\", \"FGHIJ\" ]",
                "<ol><li>ABC</li><li>DE</li><li>FGHIJ</li></ol>");
    }

    @Test
    public void testToJsObjectList3() {
        List<Object> l = new ArrayList<Object>();
        l.add(new Integer(77));
        l.add(-88);
        l.add(list1);
        l.add("ksjhdkjsdh");
        l.add(map1);
        execute(l, "[ 77, -88, [ \"ABC\", \"DE\", \"FGHIJ\" ], \"ksjhdkjsdh\", { \"zero\" : 0, \"one\" : 1, \"two\" : 2 } ]",
                "<ol><li>77</li><li>-88</li><li><ol><li>ABC</li><li>DE</li><li>FGHIJ</li></ol></li><li>ksjhdkjsdh</li><li><dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl></li></ol>");
    }

    @Test
    public void testToJsObjectArray1() {
        int arr1[] = {1,2,3,4};
        execute(arr1, "[ 1, 2, 3, 4 ]",
                "<ol><li>1</li><li>2</li><li>3</li><li>4</li></ol>");
    }

    @Test
    public void testToJsObjectArray2() {
        boolean arr2[] = {true, true, false};
        execute(arr2, "[ true, true, false ]",
                "<ol><li>true</li><li>true</li><li>false</li></ol>");
    }

    @Test
    public void testToJsObjectArray3() {
        float arr3[] = {1.00002f, 3.00006f};
        execute(arr3, "[ 1.00002, 3.00006 ]",
                "<ol><li>1.00002</li><li>3.00006</li></ol>");
    }

    @Test
    public void testToJsObjectArray4() {
        Object arr[] = { -999 };
        execute(arr, "[ -999 ]", "<ol><li>-999</li></ol>");
    }

    @Test
    public void testToJsObjectArray9() {
        Object arr9[] = { 1, 2, map1, "string"};

        execute(arr9, "[ 1, 2, { \"zero\" : 0, \"one\" : 1, \"two\" : 2 }, \"string\" ]",
                "<ol><li>1</li><li>2</li><li><dl><dt>zero</dt><dd>0</dd><dt>one</dt><dd>1</dd><dt>two</dt><dd>2</dd></dl></li><li>string</li></ol>");
    }

    @Test
    public void testToJsObjectEmpty1() {
        List<Double> l = new ArrayList<>();
        execute(l, "[  ]", "<ol></ol>");
    }

    @Test
    public void testToJsObjectEmpty2() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        execute(map, "{  }", "<dl></dl>");
    }
    
    @Test
    public void testToJsObjectEmpty3() {
        String str = "";
        execute(str, "\"\"", "");
    }

    /**
     * Negative Tests
     */
    @Test (expected=IllegalArgumentException.class)
    public void testToJsObjectNegative1() {
        Object o = null;
        execute(o, "", "");
    }

    @Test (expected=IllegalArgumentException.class)
    public void testToJsObjectNegative2() {
        Object arr[] = { null };
        execute(arr, "", "");
    }

    @Test (expected=IllegalArgumentException.class)
    public void testToJsObjectNegative3() {
        Object arr[] = { 3, "skjdhs", null };
        execute(arr, "", "");
    }
}
