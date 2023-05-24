/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.sql.PreparedStatement;
import javax.swing.JComboBox;

public class Modelos {

    private int idModelo;
    private int idMarca;
    private String nombreMarca;
    private String nombreModelo;
    private String descripcion;
    private Date movFeachaAlta;
    private Date movHoraAlta;
    private String cbxItems;

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getMovFeachaAlta() {
        return movFeachaAlta;
    }

    public void setMovFeachaAlta(Date movFeachaAlta) {
        this.movFeachaAlta = movFeachaAlta;
    }

    public Date getMovHoraAlta() {
        return movHoraAlta;
    }

    public void setMovHoraAlta(Date movHoraAlta) {
        this.movHoraAlta = movHoraAlta;
    }

    public String getCbxItems() {
        return cbxItems;
    }

    public void setCbxItems(String cbxItems) {
        this.cbxItems = cbxItems;
    }

    public void MostarTablaModel(JTable tbTotal) {
        Conexion conexion = Conexion.getInstance();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        tbTotal.setRowSorter(ordenarTabla);

        String sql = "";

        modelo.addColumn("IDMODELO");
        modelo.addColumn("IDMARCA");
        modelo.addColumn("NOMBREMARCA");
        modelo.addColumn("NOMBREMODELO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("MOV_FECHA_ALTA");
        modelo.addColumn("MOV_HORA_ALTA");

        tbTotal.setModel(modelo);

        sql = "select mo.IDMODELO, m.IDMARCA, m.NOMBREMARCA, mo.NOMBREMODELO, mo.DESCRIPCION, mo.MOV_FECHA_ALTA, mo.MOV_HORA_ALTA from modelos mo inner join marcas m on m.IDMARCA = mo.IDMARCA where m.ELIMINADO = 'true' and mo.ELIMINADO = 'true'";

        String[] dao = new String[7]; // Ajustar la longitud a 6
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
                dao[6] = rs.getString(7);

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

    public void SeleccionarModelo(JTable tbTotalModel, JTextField txtIDMARCA, JTextField txtIDMODELO, JTextField txtNombreModel, JTextField txtNombreMar, JTextField txtDescripcion, JTextField txtFechaAlta, JTextField txtHoraAlta) {
        try {
            int fila = tbTotalModel.getSelectedRow();

            if (fila >= 0) {
                txtIDMODELO.setText(tbTotalModel.getValueAt(fila, 0).toString());
                txtIDMARCA.setText(tbTotalModel.getValueAt(fila, 1).toString());
                txtNombreMar.setText(tbTotalModel.getValueAt(fila, 2).toString());
                txtNombreModel.setText(tbTotalModel.getValueAt(fila, 3).toString());
                txtDescripcion.setText(tbTotalModel.getValueAt(fila, 4).toString());

                txtFechaAlta.setText("");
                if (tbTotalModel.getValueAt(fila, 5) != null && tbTotalModel.getValueAt(fila, 5).toString() != null && !tbTotalModel.getValueAt(fila, 5).toString().isEmpty()) {
                    txtFechaAlta.setText(tbTotalModel.getValueAt(fila, 5).toString());
                }

                txtHoraAlta.setText("");
                if (tbTotalModel.getValueAt(fila, 6) != null && tbTotalModel.getValueAt(fila, 6).toString() != null && !tbTotalModel.getValueAt(fila, 6).toString().isEmpty()) {
                    txtHoraAlta.setText(tbTotalModel.getValueAt(fila, 6).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo selecionar la columna:error " + e.toString());
        }

    }

    public void InsertarModelo(JTextField txtNombreModel, JTextField txtDescripcion, JTextField txtFechaAlta, JTextField txtHoraAlta, JComboBox cbxItems) {
        setNombreModelo(txtNombreModel.getText());
        setDescripcion(txtDescripcion.getText());

        Conexion conexion = Conexion.getInstance();
        Connection micoConnection = conexion.getConexion();
        String opcionSeleccionada = cbxItems.getSelectedItem().toString();
        String consulta = "";

        try {
            Date fechaActual = new Date();

            if (cbxItems.getSelectedIndex()>= 0) {
                consulta = "INSERT INTO modelos (NOMBREMODELO, DESCRIPCION, MOV_FECHA_ALTA, MOV_HORA_ALTA, IDMARCA) VALUES (?, ?, ?, ?, ?)";
            }

            PreparedStatement cs = micoConnection.prepareStatement(consulta);
            cs.setString(1, getNombreModelo());
            cs.setString(2, getDescripcion());
            cs.setString(3, Util.convertirFecha(fechaActual));
            cs.setString(4, Util.convertirHora(fechaActual));
            cs.setString(5, opcionSeleccionada);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó el registro correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se insertó el registro correctamente: " + e.toString());
        }
    }

}
