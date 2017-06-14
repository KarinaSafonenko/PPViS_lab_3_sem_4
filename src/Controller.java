import java.awt.Point;

/**
 * Created by Admin on 21.05.2017.
 */
public class Controller {
    Points points;

    Table table;

    double border = 1.76;
    int start = 0;
    double h = 0.01;
    int hundred = 100;
    int tenThousand = 10000;
    int k;

    Controller(MainWindow mainWindow){
        this.table = mainWindow.resultTable;
        points = new Points();
    }

    public int factorial (int num) {

        return (num == 0) ? 1 : num * factorial (num - 1);
    }

   /* public void func(){
        double xi = (double) start;
        double last_slag;
        while (xi < border){
            k=0;
            last_slag = (Math.pow(-1,k)*Math.pow(xi, 2*k))/factorial(2*k);
            resX = Math.rint(xi*hundred)/hundred;
            double res = 0.0;
            for (int stop = 0; stop <= 16; stop++) {
                res += last_slag;
                k++;
                last_slag = (Math.pow(-1,k)*Math.pow(xi, 2*k))/factorial(2*k);
            }
            resY = Math.rint(res*tenThousand)/tenThousand;
            xi += h;
            points.addX(resX);
            points.addY(resY);
        }
    }*/

    public void addPoints(double x, double y){
        points.addX(x);
        points.addY(y);

    }

    public void updateData(){
       // func();
        table.SetData(points);
    }

    public Points getPoints() {
        return points;
    }

}
