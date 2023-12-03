package project1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class historique extends JFrame {
    Statement st;
    Conneccion con = new Conneccion();
    ResultSet rst;
    JScrollPane scroll;
    JTable table;

    public historique() {
        this.setTitle("Restaurant Soltana");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent ev) {
        JPanel pn = new JPanel();
        JLabel lbtitre1 = new JLabel("Historiques des commandes");
        lbtitre1.setFont(new Font("Arial", Font.BOLD, 18));
        pn.add(lbtitre1);

        // Afficher la liste des commandes
        DefaultTableModel df = new DefaultTableModel();
        table = new JTable();
        scroll = new JScrollPane(table);
        pn.add(scroll);

        df.addColumn("Numéro de table");
        df.addColumn("Commande");
        df.addColumn("Quantité");
        df.addColumn("Date commande");
        table.setModel(df);

        String req = "SELECT * FROM tb_historique ORDER BY date_commande DESC";
        try {
            st = con.laConnection().createStatement();
            rst = st.executeQuery(req);
            while (rst.next()) {
                df.addRow(new Object[] { rst.getString("tab_num"), rst.getString("aliment"),
                        rst.getString("qte_aliment"), rst.getString("date_commande") });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately, e.g., show an error message
        }

        JButton retour = new JButton("Retour");
        retour.setBounds(400, 430, 230, 25);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Commande c = new Commande();
                c.setVisible(true);
            }
        });

        pn.add(retour);

        // Set up the frame
        add(pn);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // You may want to adjust this based on your needs
        setVisible(true);
    }
}

