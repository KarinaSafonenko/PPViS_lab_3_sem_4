import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import  java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.awt.Rectangle;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Admin on 21.05.2017.
 */
public class MainWindow {

    Table resultTable;
    GraphPanel panel;
    ButtonPanel buttons;
    Controller controller;
    Function function;
    JScrollPane  pane;
    JFrame baseWindow;

    MainWindow(){
        baseWindow = new JFrame("Create graphic");
        baseWindow.getContentPane().setLayout(new FlowLayout());
        baseWindow.setSize(750,520);
        baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultTable = new Table(this);
        controller = new Controller(this);
        buttons = new ButtonPanel();
        panel = new GraphPanel(controller, 500, 400);
        pane = new JScrollPane(panel);
        pane.setPreferredSize(new Dimension(503,403));
        pane.setAutoscrolls(true);
        MoveListener listener = new MoveListener();
        pane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        pane.getViewport().addMouseListener(listener);
        pane.getViewport().addMouseMotionListener(listener);
        pane.addMouseWheelListener(new ZoomListener());
        int res = (int) (panel.getSize().getHeight()/panel.getInitialSize().getHeight()*100 - 100);
        buttons.getScale().setText("Увеличение на " + res + "%");
        baseWindow.add(resultTable.getScroll());
        baseWindow.add(pane);
        baseWindow.add(buttons.getPanel());
        baseWindow.setVisible(true);

        buttons.getCount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                function = new Function(getMainWindow());
                function.start();
                buttons.getCount().setEnabled(false);
            }
        });

        buttons.getChangeScale().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = Integer.valueOf(buttons.getNewScale().getText());
                buttons.getNewScale().setText(null);
                if (res>=0){
                   if (res/25 >= 1) {
                       int mult = (int) Math.floor(res/25);
                       panel.setFontSize(panel.getInitialFontSize()+3*mult);
                   }
                   if (res/50 >= 1){
                       int mult = (int) Math.floor(res/50);
                       panel.setPenSize(panel.getInitialPenSize()+mult);
                   }
                   if (res <= 25){
                       panel.setFontSize(panel.getInitialFontSize());
                       panel.setPenSize(panel.getInitialPenSize());
                   }
                   int zoomHeight = (int)(res*panel.getInitialSize().getHeight()/100);
                   int zoomWidth = (int)(res*panel.getInitialSize().getWidth()/100);
                   Dimension newSize = new Dimension((int)panel.getInitialSize().getWidth()+zoomWidth, (int)panel.getInitialSize().getHeight()+zoomHeight);
                   buttons.getScale().setText("Увеличение на " + res + "%");
                   panel.setPreferredSize(newSize);
                   panel.setSize(newSize);
                   panel.revalidate();
                }
            }
        });
    }

    class MoveListener extends MouseAdapter {

        private Point start;

        @Override
        public void mousePressed(MouseEvent e) {
            start = e.getPoint();
        }


        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (start != null) {
                JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, panel);
                if (viewPort != null) {
                    int deltaX = start.x - e.getX();
                    int deltaY = start.y - e.getY();
                    Rectangle view = viewPort.getViewRect();
                    view.x += deltaX*0.1;
                    view.y += deltaY*0.1;
                    panel.scrollRectToVisible(view);
                }
            }
        }
    }

    private class ZoomListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getPreciseWheelRotation() < 0 && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                Dimension newSize = new Dimension(panel.getWidth()+125, panel.getHeight()+100);
                panel.setPreferredSize(newSize);
                panel.setSize(newSize);
                panel.setFontSize(panel.getFontSize()+3);
                int res = (int) (panel.getSize().getHeight()/panel.getInitialSize().getHeight()*100 - 100);
                if (res%50 == 0){
                    panel.setPenSize(panel.getPenSize()+1);
                }
                buttons.getScale().setText("Увеличение на " + res + "%");
                panel.revalidate();
            }
            if (e.getPreciseWheelRotation() > 0 && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                if (panel.getWidth() > panel.getInitialSize().getWidth()) {
                    Dimension newSize = new Dimension(panel.getWidth() - 125, panel.getHeight() - 100);
                    panel.setPreferredSize(newSize);
                    panel.setSize(newSize);
                    if (panel.getFontSize() > 15) {
                        panel.setFontSize(panel.getFontSize()-3);
                    }
                    int res = (int) (panel.getSize().getHeight()/panel.getInitialSize().getHeight()*100 - 100);
                    if (res%50 == 0 && res >0){
                        panel.setPenSize(panel.getPenSize()-1);
                    }
                    if (res <= 25) panel.setPenSize(1);
                    buttons.getScale().setText("Увеличение на " + res + "%");
                    panel.revalidate();
                    baseWindow.repaint();
                }
            }
        }
    }

    public  MainWindow getMainWindow(){
        return  this;
    }

    public Controller getController() {
        return controller;
    }

    public Table getResultTable() {
        return resultTable;
    }
}
