import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import  java.awt.Dimension;
import javax.swing.JScrollPane;

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
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.addMouseWheelListener(new ZoomListener());
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
    }

    class MoveListener extends java.awt.event.MouseAdapter {

        private Point start;

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            start = e.getPoint();
        }


        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
            if (start != null) {
                javax.swing.JViewport viewPort = (javax.swing.JViewport) javax.swing.SwingUtilities.getAncestorOfClass(javax.swing.JViewport.class, panel);
                if (viewPort != null) {
                    int deltaX = start.x - e.getX();
                    int deltaY = start.y - e.getY();
                    java.awt.Rectangle view = viewPort.getViewRect();
                    view.x += deltaX;
                    view.y += deltaY;
                    panel.scrollRectToVisible(view);
                    baseWindow.repaint();
                }
            }
        }
    }

    private class ZoomListener implements java.awt.event.MouseWheelListener {
        @Override
        public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
            if (e.getPreciseWheelRotation() < 0 && ((e.getModifiers() & java.awt.event.KeyEvent.CTRL_MASK) != 0)) {
                System.out.println("Case 1");
                Dimension newSize = new Dimension(panel.getWidth()+100, panel.getHeight()+100);
                panel.setPreferredSize(newSize);
                panel.setSize(newSize);
                System.out.println(panel.getSize());
                panel.setFontSize(panel.getFontSize()+3);
                int res = (int) (panel.getSize().getHeight()/panel.getInitialSize().getHeight()*100 - 100);
                buttons.getScale().setText("Увеличение на " + res + "%");
                panel.revalidate();
            }
            if (e.getPreciseWheelRotation() > 0 && ((e.getModifiers() & java.awt.event.KeyEvent.CTRL_MASK) != 0)) {
                if (panel.getWidth() > panel.getInitialSize().getWidth()) {
                    System.out.println("Case 2");
                    Dimension newSize = new Dimension(panel.getWidth() - 100, panel.getHeight() - 100);
                    panel.setPreferredSize(newSize);
                    panel.setSize(newSize);
                    System.out.println(panel.getSize());
                    if (panel.getFontSize() > 15) {
                        panel.setFontSize(panel.getFontSize()-3);
                    }
                    int res = (int) (panel.getSize().getHeight()/panel.getInitialSize().getHeight()*100 - 100);
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

    public GraphPanel getPanel() {
        return panel;
    }
}
