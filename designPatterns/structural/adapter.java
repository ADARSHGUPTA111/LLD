/**
 * Adapter design pattern is used in scenarios where we want 2 incompatible types to collaborate.
 * One typical example of this scenario is when we want data from obtained from a certain 
 * API/database to be utilised in a different
 * code which is expecting a different schema.
 * It is used to make legacy code compatible with new code.
 * In this example, Summary class is legacy code which is compatible with OldEmployee class 
 * but not NewEmployee.
 * We don't want to modify legacy code.
 * So we create an adapter for NewEmployee which extends OldEmployee and hence is compatible
 * with Summary.
 */


 class JSONAnalyticsTool {
    private String jsonData;

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public void AnalyzeData() {
        if (jsonData.contains("json")) {
            System.out.println("Analyzing JSON Data - " + jsonData);
        } else {
            System.out.println("Not in the correct format. Can't analyze!");
        }
    }
}

interface AnalyticsTool {
    void AnalyzeData();
}

class XMLToJSONAdapter implements AnalyticsTool {
    private JSONAnalyticsTool jsonAnalyticsTool;

    public XMLToJSONAdapter(String xmlData) {
        System.out.println("Converting the XML Data '" + xmlData + "' to JSON Data!");
        String newData = xmlData + " in json";
        jsonAnalyticsTool = new JSONAnalyticsTool();
        jsonAnalyticsTool.setJsonData(newData);
    }

    public void AnalyzeData() {
        jsonAnalyticsTool.AnalyzeData();
    }
}

public class XmlToJson {
    public static void main(String[] args) {
        String xmlData = "Sample Data";
        JSONAnalyticsTool tool1 = new JSONAnalyticsTool();
        tool1.setJsonData(xmlData);
        tool1.AnalyzeData();

        System.out.println("----------------------------------------------");

        AnalyticsTool tool2 = new XMLToJSONAdapter(xmlData);
        tool2.AnalyzeData();
    }
}