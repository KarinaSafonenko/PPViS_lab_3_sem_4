import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Created by Admin on 21.05.2017.
 */
public class ButtonPanel {
    JPanel panel;
    JButton count;
    JLabel scale;

    ButtonPanel(){
        panel = new JPanel();
        panel.setPreferredSize(new java.awt.Dimension(300,300));
        panel.setOpaque(true);

        count = new JButton("Построить график");
        scale = new JLabel();

        panel.add(count);
        panel.add(scale);
        panel.setVisible(true);
    }

    public JButton getCount() {
        return count;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getScale() {
        return scale;
    }
}
