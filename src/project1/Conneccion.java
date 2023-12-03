package project1;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Conneccion {
	    Connection cn;

	    public Conneccion() {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            cn = DriverManager.getConnection("jdbc:mysql://localhost/resto_db", "root", "");
	            System.out.println("Connection établie!");
	        } catch (Exception e) {
	            System.out.println("Non connectée!");
	        }
	    }

	    public Connection laConnection() {
	        return cn;
	    }

	    public void closeConnection() {
	        if (cn != null) {
	            try {
	                cn.close();
	                System.out.println("Connection fermée!");
	            } catch (SQLException e) {
	                System.out.println("Erreur lors de la fermeture de la connexion!");
	            }
	        }
	    }
	}



