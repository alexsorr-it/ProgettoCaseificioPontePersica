package JAVA;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings({"SqlResolve", "FieldCanBeLocal"})
public class ShowProductBrowseServiceJSP {
    boolean removeComma = false;
    private Connection con;

    public String getShowProductBrowseToJSONJSP(MySqlDbConnection db) {
        String showProductBrowseJSONFormat = "{\"id\":\"{ID}\",\"nome_prodotto\":\"{NOME_PRODOTTO}\",\"provenienza\":\"{PROVENIENZA}\",\"tempo_preparazione\":\"{TEMPO_PREPARAZIONE}\", \"prezzo\":\"{PREZZO}\", \"descrizione\":\"{DESCRIZIONE}\"}";
        StringBuilder showProductJSONResult = new StringBuilder();
        String JSONRow;
        this.con = db.connect();
        try {
            Statement stmt = this.con.createStatement();
            ResultSet rs;
            //fetch activities
            rs = stmt.executeQuery("SELECT * FROM Prodotto");
            //JSON realization
            while (rs.next()) {
                removeComma = true;
                JSONRow = showProductBrowseJSONFormat.replace("{ID}", Integer.toString(rs.getInt(1)));
                JSONRow = JSONRow.replace("{NOME_PRODOTTO}", JAVA.Util.utf8Encode(rs.getString(2)));
                JSONRow = JSONRow.replace("{PROVENIENZA}", JAVA.Util.utf8Encode(rs.getString(3)));
                JSONRow = JSONRow.replace("{TEMPO_PREPARAZIONE}", Integer.toString(rs.getInt(4)));
                JSONRow = JSONRow.replace("{PREZZO}", Float.toString(rs.getFloat(5)));
                JSONRow = JSONRow.replace("{DESCRIZIONE}", JAVA.Util.utf8Encode(rs.getString(6)));
                //JSONRow = JSONRow.replace("{MEDIA}", Image.toString(rs.getBlob(7)));
                showProductJSONResult.append(JSONRow).append(",");
            }
            this.con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (removeComma)
            return "[" + JAVA.Util.removeLastChar(showProductJSONResult.toString()) + "]";
        else
            return "[" + showProductJSONResult + "]";
    }
}
