package kata5p1;

import java.util.List;
/**
 *
 * @author NassrEML
 */
public class Kata5P1 {

    public static void main(String[] args) {
        MailListReader lector = new MailListReader();
        String ruta = "email.txt";
        List<String> lista = lector.read(ruta);
        SelectApp app = new SelectApp(lista);
        app.createNewTableEmail();
        app.insert();
    }
}
