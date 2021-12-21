import com.app.App;
import com.app.menu.*;

import java.io.IOException;

/**
 * com.app
 * Create by Le Nguyen Tu Van
 * Date 12/16/2021 - 8:59 PM
 * Description: ...
 */
public class Main {
    public static void main(String[] args) {
        App app = null;
        try {
            app = new App();
            app.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
