import java.awt.Point;

/**
 * Created by Admin on 21.05.2017.
 */
public class Controller {
    Points points;

    Table table;

    Controller(MainWindow mainWindow){
        this.table = mainWindow.resultTable;
        points = new Points();
    }

    public void addPoints(double x, double y){
        points.addX(x);
        points.addY(y);

    }

    public Points getPoints() {
        return points;
    }

}
