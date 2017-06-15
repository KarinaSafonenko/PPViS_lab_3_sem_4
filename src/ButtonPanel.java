import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;

/**
 * Created by Admin on 21.05.2017.
 */
public class ButtonPanel {
    JPanel panel;
    JButton count;
    JLabel scale;
    JButton changeScale;
    JTextField newScale;

    ButtonPanel(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350,400));
        panel.setOpaque(true);

        count = new JButton("Построить график");
        scale = new JLabel();
        changeScale = new JButton("Применить увеличение на (%): ");
        newScale = new JTextField(5);

        panel.add(count);
        panel.add(scale);
        panel.add(changeScale);
        panel.add(newScale);
        panel.setVisible(true);
    }

    public JButton getChangeScale() {
        return changeScale;
    }

    public JTextField getNewScale() {
        return newScale;
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
