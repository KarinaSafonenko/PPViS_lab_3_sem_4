/**
 * Created by Admin on 30.05.2017.
 */
public class Function extends Thread{

    MainWindow mainWindow;
    double border = 1.76;
    int start = 0;
    double h = 0.01;
    int hundred = 100;
    int tenThousand = 10000;
    int k;
    double resX;
    double resY;

    Function(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void run() {
        super.run();
        {
            synchronized (this) {
                double xi = (double) start;
                double last_slag;
                while (xi < border) {
                    k = 0;
                    last_slag = (Math.pow(-1, k) * Math.pow(xi, 2 * k)) / factorial(2 * k);
                    resX = Math.rint(xi * hundred) / hundred;
                    double res = 0.0;
                    for (int stop = 0; stop <= 16; stop++) {
                        res += last_slag;
                        k++;
                        last_slag = (Math.pow(-1, k) * Math.pow(xi, 2 * k)) / factorial(2 * k);
                    }
                    resY = Math.rint(res * tenThousand) / tenThousand;
                    xi += h;
                    mainWindow.getController().addPoints(resX, resY);
                    mainWindow.getResultTable().SetData(mainWindow.getController().getPoints());
                    try {
                        Thread.sleep(50);
                        mainWindow.baseWindow.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public int factorial (int num) {
        return (num == 0) ? 1 : num * factorial (num - 1);
    }
}
