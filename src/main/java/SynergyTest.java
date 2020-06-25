// (C) 2020 PPI AG

import javax.swing.*;

/**
 * @author PPI AG
 */
public class SynergyTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    JFrame frame = new JFrame("Window");
                    JPanel jPanel = new JPanel();
                    frame.setContentPane(jPanel);
                    jPanel.add(new JTextArea(5,5));
                    frame.pack();
                    frame.setVisible(true);
                }
        );
    }
}
