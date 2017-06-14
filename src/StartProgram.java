import javax.swing.SwingUtilities;



/**
 * Created by Admin on 21.05.2017.
 */
public class StartProgram {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });
    }
}
