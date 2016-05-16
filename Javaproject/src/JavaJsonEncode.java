/**
 * Created by Richa on 15/5/16.
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaJsonEncode{
    public static void main(String args[]){
        System.out.println("---------------Java JSON Encode-----------");

        printJavaJsonEncode();

        System.out.println("------Java JSON Encode using MAP ---------");

        printJavaJsonEncodeUsingMap();

        System.out.println("-------Java JSON Array Encode-------------");
        printJavaJsonArrayEncode();


        System.out.println("---Java JSON Array Encode using List------");
        printJavaJsonArrayEncodeUsingList();

        System.out.println("---------Java JSON Decode  ----------------");
        printJavaJsonDecode();

    }
    static void printJavaJsonEncode()
    {   JSONObject obj=new JSONObject();
        obj.put("name", "TestEmployee_1");
        obj.put("salary", new Double(50000));
        System.out.println(obj);

        //Object Streaming
        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonText = out.toString();
        System.out.println(jsonText);

    }
    static void printJavaJsonEncodeUsingMap()
    {
        Map obj=new HashMap();
        obj.put("name","TestEmployee_2");
        obj.put("age", new Integer(25));
        obj.put("salary", new Double(600000));
      String jsonText = JSONValue.toJSONString(obj);
        System.out.println(jsonText);

    }
    static void printJavaJsonArrayEncode()
    {
        JSONArray arr = new JSONArray();
        arr.add("TesrtEmploy_3");
        arr.add(new Integer(25));
        arr.add(new Double(100000));
        System.out.println(arr);

    }
    static void printJavaJsonArrayEncodeUsingList()
    {
        List arr = new ArrayList();
        arr.add("TestEmployee_4");
        arr.add(new Integer(19));
        arr.add(new Double(25000));
        String jsonText = JSONValue.toJSONString(arr);
        System.out.println(jsonText);

    }

    static void printJavaJsonDecode()
    {
        String s="{\"name\":\"TestEmployee_5\",\"salary\":70000.0,\"age\":24}";
        Object obj=JSONValue.parse(s);
        JSONObject jsonObject = (JSONObject) obj;

        String name = (String) jsonObject.get("name");
        double salary = (Double) jsonObject.get("salary");
        long age = (Long) jsonObject.get("age");
        System.out.println(name+" "+salary+" "+age);

    }
}