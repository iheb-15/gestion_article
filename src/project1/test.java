package project1;

import javax.swing.SwingUtilities;

public class test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage cmd = new LoginPage();
            cmd.setVisible(true);
        });
    }
}
