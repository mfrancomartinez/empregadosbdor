package pempregadosbdor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class Conexion {
    String driver = "jdbc:oracle:thin:";
          String  host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
         String   porto = "1521";
          String  sid = "orcl";
          String  usuario = "hr";
          String  password = "hr";
          String  url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
            Connection conn;
            Statement st=null;
            ResultSet rs=null;
            PreparedStatement ps=null;

    public Conexion() {
       
        try {
            
                this.conn = DriverManager.getConnection(url);
                if (conn != null) {
                        System.out.println("Conectado.");
                    } else {
                        System.out.println("Error en la conexión.");
                    }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
}
    }
    
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
}
    
    
    public void listar() {
        try {
            ps=conn.prepareStatement("select * from empregadosbdor");
                rs=ps.executeQuery();
             
             
            while (rs.next()) {
                
                int id = rs.getInt("id");
                java.sql.Struct emp = (java.sql.Struct) rs.getObject("emp");
                Object[] attributes = emp.getAttributes();  
                String nome = (String) attributes[0];
                java.math.BigDecimal eda = (java.math.BigDecimal) attributes[1];
                int edade = eda.intValue();
                int prezo = rs.getInt("salario");
                System.out.println("id"+id +"edade"+ edade +"prezo"+ prezo);
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
}
 public void buscar_por_clave(int clave) {
        try {
            ps=conn.prepareStatement("select * from empregadosbdor where id=?");
            ps.setInt(1,clave);
            rs=ps.executeQuery();
             rs.next();
               int id = rs.getInt("id");
                java.sql.Struct emp = (java.sql.Struct) rs.getObject("emp");
                Object[] attributes = emp.getAttributes();  
                String nome = (String) attributes[0];
                java.math.BigDecimal eda = (java.math.BigDecimal) attributes[1];
                int edade = eda.intValue();
                int prezo = rs.getInt("salario");
                System.out.println("id"+id +"edade"+ edade +"prezo"+ prezo);
            
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
}  

    public void insertar(int id, String nome, int edade, int salario) throws SQLException {
        
        Statement st = conn.createStatement();
        st.executeUpdate("insert into empregadosbdor values ("
                +id+","
                +"tipo_emp('"+
                nome+"',"+
                edade+"),"+
                salario+")"
        );
         
            
            System.out.println("insert realizado");
        
    }

    public void actuFila(String cod,int prezo){
        try {
            ps=conn.prepareStatement("update from produtos set prezo=? where codigo=?;");
            
                ps.setInt(1,prezo);
                ps.setString(2,cod);
                ps.executeQuery();
            
            System.out.println("Actualizacion do prezo de: "+cod+" realizada correctamente a "+prezo);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
