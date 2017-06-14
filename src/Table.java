import javax.swing.JTable;
import  javax.swing.JScrollPane;
import java.awt.Dimension;
/**
 * Created by Admin on 21.05.2017.
 */
public class Table {

    JTable table;
    JScrollPane scroll;
    Points points;
    MainWindow mainWindow;

    public Table(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        String[] headings = {"X", "Y"};
        Object[][] info = new Object[176][2];
        table = new JTable(info,headings);
        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(new Dimension(150,350));
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void SetData(Points points){
        this.points = points;
        for (int i=0; i<points.getSizeX(); i++){
            table.setValueAt(points.xList.get(i), i, 0);
            table.setValueAt(points.yList.get(i), i, 1);
        }
    }
}
