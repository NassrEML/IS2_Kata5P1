package kata5p1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NassrEML
 */

public class SelectApp {
    
    // Se conecta a la BD y se devuelve un objeto Connection
    private Connection connect() {
    // SQLite connection string
        String url = "jdbc:sqlite:Kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void selectAllPeople(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            // Se itera sobre los registros
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + " => " +
                rs.getString("Name") + " => " +
                rs.getString("Apellidos") + " => " +
                rs.getString("Departamento"));
            }   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTableEmail() {
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " direccion text NOT NULL);";
        try (Connection conn = this.connect()) {
            Statement stmt = conn.createStatement();
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}