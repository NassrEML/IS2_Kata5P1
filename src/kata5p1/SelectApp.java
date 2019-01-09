package kata5p1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author NassrEML
 */

public class SelectApp {
    
    private List<String> lista;

    public SelectApp(List<String> lista) {
        this.lista = lista;
    }
    
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
    
    public void insert(){
        String sql = "INSERT INTO EMAIL(direccion) VALUES(?)";
        try  (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             for (String string : lista) {
                  pstmt.setString(1,string);
                  pstmt.executeUpdate();
             }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}