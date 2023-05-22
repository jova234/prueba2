/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Modelos {

    public void MostarTablaModel(JTable tbTotal) {
        Conexion conexion = Conexion.getInstance();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        tbTotal.setRowSorter(ordenarTabla);

        String sql = "";

        modelo.addColumn("IDMARCA");
        modelo.addColumn("NOMBREMARCA");
        modelo.addColumn("NOMBREMODELO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("MOV_FECHA_ALTA");
        modelo.addColumn("MOV_HORA_ALTA");

        tbTotal.setModel(modelo);

        sql = "SELECT mo.IDMARCA, m.NOMBREMARCA, mo.NOMBREMODELO, mo.DESCRIPCION, mo.MOV_FECHA_ALTA, mo.MOV_HORA_ALTA "
                + "FROM marcas m "
                + "INNER JOIN modelos mo ON m.IDMARCA = mo.IDMARCA "
                + "WHERE m.ELIMINADO = 'true'";

        String[] dao = new String[6]; // Ajustar la longitud a 6
        Statement st;

        try {
            st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dao[0] = rs.getString(1);
                dao[1] = rs.getString(2);
                dao[2] = rs.getString(3);
                dao[3] = rs.getString(4);
                dao[4] = rs.getString(5);
                dao[5] = rs.getString(6);

                modelo.addRow(dao);

                if (tbTotal != null) {
                    tbTotal.getTableHeader().setReorderingAllowed(false);
                }
                tbTotal.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede mostar la tabla " + e.toString());
            e.printStackTrace();
        }
    }

}
