package JAVA.Tester;

import JAVA.MySqlDbConnection;
import JAVA.ShowProductBrowseServiceJSP;

public class ShowProductBrowseServiceJSPTester {
    public static void main(String[] args) {
        //Database initialization
        MySqlDbConnection db = MySqlDbConnection.getInstance();
        //Test JSON
        ShowProductBrowseServiceJSP service = new ShowProductBrowseServiceJSP();
        System.out.println(service.getShowProductBrowseToJSONJSP(db));
    }
}
