import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonStreamingApiExample {
    public static void main(String[] args) {
        System.out.println("------------- Reading JSON DATA USING PARSER ------------------------");
        String personJsonData = fetchPersonJsonData();
        ReadingJsonDataUsingParser(personJsonData);

        System.out.println("------------- Writing JSON Data Using a Generator ------------------------");
        writeJsonDataUsingGenerator();

    }


    private static String fetchPersonJsonData() {
        String personJSONData =
                "  {" +
                        "   \"name\": \"Employye1\", " +
                        "   \"age\" : 20, " +
                        "   \"isMarried\" : false, " +
                        "   \"address\": { " +
                        "     \"street\": \"#1234, Delhi\", " +
                        "     \"zipCode\": \"110092\" " +
                        "   }, " +
                        "   \"phoneNumbers\": [\"888000999\", \"011-555588\"] " +
                        " }";
        return personJSONData;
    }


    private static void ReadingJsonDataUsingParser(String jsonData) {

        JsonParser parser = Json.createParser(new StringReader(jsonData));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.print(event.toString() + " " +
                            parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " " +
                            parser.getString());
                    break;
            }
        }
    }

    private static void writeJsonDataUsingGenerator() {
        FileWriter writer;
        try {
            writer = new FileWriter("test.txt");

            JsonGenerator gen = Json.createGenerator(writer);
            gen.writeStartObject()
                    .write("firstName", "TestName1")
                    .write("lastName", "xyz")
                    .write("age", 18)
                    .write("streetAddress", "abc street")
                    .write("city", "Delhi")
                    .write("state", "Delhi")
                    .write("postalCode", "58954565")
                    .writeStartArray("phoneNumbers")
                    .writeStartObject()
                    .write("type", "mobile")
                    .write("number", "9835416298")
                    .writeEnd()
                    .writeStartObject()
                    .write("type", "home")
                    .write("number", "879546624856")
                    .writeEnd()
                    .writeEnd()
                    .writeEnd();
            gen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}