import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.BasicStroke;

/**
 * Created by Admin on 21.05.2017.
 */
public class GraphPanel extends JPanel {
    List<Double> newPointsX;
    List<Double> newPointsY;
    Controller controller;
    int hundred = 100;
    int prevX;
    int prevY;
    Dimension size;
    int penSize;
    Dimension initialSize;
    int fontSize;
    int initialFontSize = 15;
    int initialPenSize = 1;

    GraphPanel(Controller controller, int width, int height) {
        this.controller = controller;
        size = new Dimension(width, height);
        initialSize = size;
        penSize = 1;
        fontSize = 15;
        setPreferredSize(size);
        setSize(size);
    }

    public void paintComponent(Graphics g) {
        newPointsX = controller.getPoints().getxList();
        newPointsY = controller.getPoints().getyList();

        size = new Dimension( this.getWidth(), this.getHeight());
        Graphics2D gh = (Graphics2D) g;
        gh.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gh.setStroke(new BasicStroke(penSize));

        gh.drawLine((int) (0.08 * size.width), (int) (0.12 * size.height), (int) (0.08 * size.width), (int) (0.7 * size.height));
        gh.drawLine((int) (0.08 * size.width), (int) (0.12 * size.height), (int) (0.08 * size.width - 5), (int) (0.12 * size.height + 10));
        gh.drawLine((int) (0.08 * size.width), (int) (0.12 * size.height), (int) (0.08 * size.width + 5), (int) (0.12 * size.height + 10));
        gh.drawLine((int) (0.08 * size.width), (int) (0.6 * size.height), (int) (0.95 * size.width), (int) (0.6 * size.height));
        gh.drawLine((int) (0.95 * size.width), (int) (0.6 * size.height), (int) (0.95 * size.width - 10), (int) (0.6 * size.height - 5));
        gh.drawLine((int) (0.95 * size.width), (int) (0.6 * size.height), (int) (0.95 * size.width - 10), (int) (0.6 * size.height + 5));

        gh.setFont(new Font("Calibri", Font.PLAIN, fontSize));

        gh.drawString("Y", (int) (0.03 * size.width), (int) (0.15 * size.height));
        gh.drawString("X", (int) (0.94 * size.width), (int) (0.65 * size.height));
        gh.drawString("0", (int) (0.04 * size.width), (int) (0.61 * size.height));

        for (int k = 1; k <= 25; k++) {
            gh.drawLine((int) (0.08 * size.width + k * 0.0328 * size.width), (int) (0.5875 * size.height), (int) (0.08 * size.width + k * 0.0328 * size.width), (int) (0.6125 * size.height));
            if (k % 2 == 0) {
                gh.drawString(String.valueOf(Math.rint(0.07 * k * hundred) / hundred), (int) (0.08 * size.width + k * 0.0328 * size.width - 0.02 * size.width), (int) (0.65 * size.height));
            }
        }

        for (int k = 1; k <= 10; k++) {
            gh.drawLine((int) (0.08 * size.width - 0.01 * size.width), (int) (0.6 * size.height - k * 0.04125 * size.height), (int) (0.08 * size.width + 0.01 * size.width), (int) (0.6 * size.height - k * 0.04125 * size.height));
            gh.drawString(String.valueOf(Math.rint(0.1 * k * hundred) / hundred), (int) (0.08 * size.width - 0.07 * size.width), (int) (0.6 * size.height - k * 0.04125 * size.height + 0.01 * size.height));
        }
        for (int k = 1; k <= 2; k++) {
            gh.drawLine((int) (0.08 * size.width - 0.01 * size.width), (int) (0.6 * size.height + k * 0.04125 * size.height), (int) (0.08 * size.width + 0.01 * size.width), (int) (0.6 * size.height + k * 0.04125 * size.height));
            gh.drawString(String.valueOf(Math.rint(-0.1 * k * hundred) / hundred), (int) (0.08 * size.width - 0.07 * size.width), (int) (0.6 * size.height + k * 0.04125 * size.height + 0.01 * size.height));
        }

        if (newPointsX.size() != 0) {
            prevX = (int) (0.08 * size.width + (newPointsX.get(0) * 0.0328 * size.width) / 0.07);
            prevY = (int) (0.6 * size.height - (newPointsY.get(0) * 0.04125 * size.height) / 0.1);
        }
        for (int kol = 1; kol < controller.getPoints().getSizeX() - 1; kol++) {
            int xTrue = (int) (0.08 * size.width + (newPointsX.get(kol) * 0.0328 * size.width) / 0.07);
            int yTrue = (int) (0.6 * size.height - (newPointsY.get(kol) * 0.04125 * size.height) / 0.1);
            gh.drawLine(prevX, prevY, xTrue, yTrue);
            prevX = xTrue;
            prevY = yTrue;
        }
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getPenSize() {
        return penSize;
    }

    public Dimension getInitialSize() {
        return initialSize;
    }

    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    public int getInitialFontSize() {
        return initialFontSize;
    }

    public int getInitialPenSize() {
        return initialPenSize;
    }

}
