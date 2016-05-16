import java.io.StringReader;
import java.io.StringWriter;
import javax.json.*;

public class JsonObjectApiExample {
    public static void main(String[] args) {
        //creatingObjectModelFromJsonData
        System.out.println("------------- Creating ObjectModel From JsonData ------------------------");

        JsonObject objectmodel = creatingObjectModelFromJsonData();

        System.out.println("--------- Creating an Object Model from Application Code -----------------");

        //Creating an Object Model from Application Code
        creatingObjectModelFromApplicationCode();
        System.out.println("---------------- Writing Object Model To Stream ---------------------------");
        //Writing Object Model To Stream
        writeOjectModelToStream(objectmodel);
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

    private static JsonObject creatingObjectModelFromJsonData() {
        String personJSONData = fetchPersonJsonData();

        JsonReader reader = Json.createReader(new StringReader(personJSONData));

        JsonObject personObject = reader.readObject();
        reader.close();
        System.out.println("Name   : " + personObject.getString("name") + "  \n Age    : "
                + personObject.getInt("age") + " \n Married: " + personObject.getBoolean("isMarried"));

        JsonObject addressObject = personObject.getJsonObject("address");
        System.out.println("Address: \n" + addressObject.getString("street") + "\n" + addressObject.getString("zipCode"));
        System.out.println("Phone  : ");

        JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");
        for (JsonValue jsonValue : phoneNumbersArray) {
            System.out.println(jsonValue.toString());
        }
        return personObject;
    }


    private static void creatingObjectModelFromApplicationCode() {
        JsonObject model = Json.createObjectBuilder()
                .add("firstName", "Priya")
                .add("lastName", "Kumari")
                .add("age", 20)
                .add("streetAddress", "11111-xyz")
                .add("city", "DELHI")
                .add("state", "Delhi")
                .add("postalCode", "110092")
                .add("phoneNumbers", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "mobile")
                                .add("number", "888000999"))
                        .add(Json.createObjectBuilder()
                                .add("type", "home")
                                .add("number", "0115256899")))
                .build();

        navigateTree(model, null);

    }


    public static void navigateTree(JsonValue tree, String key) {
        if (key != null)
            System.out.print("Key " + key + ": ");
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet())
                    navigateTree(object.get(name), name);
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array)
                    navigateTree(val, null);
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }

    private static void writeOjectModelToStream(JsonObject objectmodel) {
        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(objectmodel);
        jsonWriter.close();

        String jsonData = stWriter.toString();
        System.out.println(jsonData);
    }

}