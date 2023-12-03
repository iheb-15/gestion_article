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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Montant extends JFrame {
    Statement st;
    Conneccion con = new Conneccion();
    ResultSet rst;
    JScrollPane scroll2;
    JTable table2;

    public Montant() {
        this.setTitle("Restaurant Soltana");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent ev) {
        JPanel pn = new JPanel();
        JLabel lbtitre1 = new JLabel("Montant total de commande");
        lbtitre1.setFont(new Font("Arial", Font.BOLD, 18));
        pn.add(lbtitre1);

        // Afficher la liste des commandes
        DefaultTableModel df2 = new DefaultTableModel();
        table2 = new JTable();
        scroll2 = new JScrollPane(table2);
        pn.add(scroll2);

        df2.addColumn("Numéro table");
        df2.addColumn("Montant total (FCFA)");
        table2.setModel(df2);

        String req2 = "SELECT tab_num, SUM(qte_aliment * prix_aliment) AS montant " +
                       "FROM tb_aliment INNER JOIN tb_commande " +
                       "ON tb_aliment.code_aliment = tb_commande.aliment " +
                       "GROUP BY tab_num";

        try {
            st = con.laConnection().createStatement();
            rst = st.executeQuery(req2);
            while (rst.next()) {
                df2.addRow(new Object[] { rst.getString("tab_num"), rst.getString("montant") });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur !", null, JOptionPane.ERROR_MESSAGE);
        }

        // Bouton pour retourner à l'interface commande
        JButton retour = new JButton("Retour");
        retour.setBounds(400, 430, 230, 25);
        retour.addActionListener(new ActionListener() {
            Commande c = new Commande();

            public void actionPerformed(ActionEvent e) {
                dispose();
                c.setVisible(true);
            }
        });

        pn.add(retour);
        add(pn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Adjust this based on your needs
        setVisible(true);
    }
}
